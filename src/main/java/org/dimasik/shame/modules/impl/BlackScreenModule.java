package org.dimasik.shame.modules.impl;

import net.minecraft.server.v1_16_R3.PacketPlayOutGameStateChange;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.dimasik.shame.Shame;
import org.dimasik.shame.modules.Module;

public class BlackScreenModule extends Module {
    public static void blackScreen(Player player, int time) {
        new BukkitRunnable() {
            int ticks = 20 * time;

            @Override
            public void run() {
                if (ticks <= 0) {
                    cancel();
                } else {
                    ticks--;
                    if (player == null) {
                        cancel();
                        return;
                    }
                    try {
                        CraftPlayer craftPlayer = (CraftPlayer) player;
                        PacketPlayOutGameStateChange.a gameStateType = new PacketPlayOutGameStateChange.a(4);
                        PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(gameStateType, 1.0F);
                        craftPlayer.getHandle().playerConnection.sendPacket(packet);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            private Class<?> getNMSClass(String name) throws ClassNotFoundException {
                String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
                return Class.forName("net.minecraft.server." + version + "." + name);
            }
        }.runTaskTimer(Shame.getInstance(), 0, 1);
    }
}
