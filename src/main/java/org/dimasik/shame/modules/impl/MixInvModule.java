package org.dimasik.shame.modules.impl;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.ItemStack;
import net.minecraft.server.v1_16_R3.PacketPlayOutSetSlot;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.dimasik.shame.Shame;
import org.dimasik.shame.modules.Module;

import java.util.Random;

public class MixInvModule extends Module {
    public static void startMixingInventory(Player player){
        new BukkitRunnable(){
            int ticks = 0;

            @Override
            public void run(){
                if(ticks >= 60 || player == null){
                    this.cancel();
                    return;
                }
                ticks++;
                mix(player);
            }
        }.runTaskTimer(Shame.getInstance(), 0, 1);
    }

    public static void mix(Player player){
        for(org.bukkit.inventory.ItemStack itemStack : player.getInventory()){
            int slot = new Random().nextInt(41);
            if(itemStack == null){
                sendSlotUpdate(player, slot, new org.bukkit.inventory.ItemStack(Material.values()[new Random().nextInt(Material.values().length)]));
            }
            else{
                sendSlotUpdate(player, slot, itemStack);
            }
        }
    }

    public static void sendSlotUpdate(Player player, int slot, org.bukkit.inventory.ItemStack itemStack) {
        ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);

        EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();

        PacketPlayOutSetSlot packet = new PacketPlayOutSetSlot(
                nmsPlayer.defaultContainer.windowId,
                slot,
                nmsItem
        );

        nmsPlayer.playerConnection.sendPacket(packet);
    }
}
