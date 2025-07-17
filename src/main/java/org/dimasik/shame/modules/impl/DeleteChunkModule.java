package org.dimasik.shame.modules.impl;

import net.minecraft.server.v1_16_R3.BlockPosition;
import net.minecraft.server.v1_16_R3.Blocks;
import net.minecraft.server.v1_16_R3.PacketPlayOutBlockChange;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.dimasik.shame.Shame;
import org.dimasik.shame.modules.Module;

public class DeleteChunkModule extends Module {
    public static void deleteChunk(Player player){
        Bukkit.getScheduler().runTaskAsynchronously(Shame.getInstance(), () -> {
            Chunk chunk = player.getChunk();
            for (int x = 0; x < 16; x++) {
                for (int y = 0; y < 256; y++) {
                    for (int z = 0; z < 16; z++) {
                        Block block = chunk.getBlock(x, y, z);
                        clearBlock(player, block.getLocation());
                    }
                }
            }
        });

    }
    private static void clearBlock(Player player, Location location) {
        PacketPlayOutBlockChange packet = new PacketPlayOutBlockChange(
                ((CraftWorld) location.getWorld()).getHandle(),
                new BlockPosition(location.getX(), location.getY(), location.getZ())
        );
        packet.block = Blocks.AIR.getBlockData();
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
