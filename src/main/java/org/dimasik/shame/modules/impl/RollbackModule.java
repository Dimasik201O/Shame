package org.dimasik.shame.modules.impl;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.dimasik.shame.Shame;
import org.dimasik.shame.modules.Module;

import java.util.ArrayDeque;
import java.util.Deque;

public class RollbackModule extends Module {
    public static void startRollback(Player player){
        Deque<Location> locations = new ArrayDeque<>();
        new BukkitRunnable(){
            int ticks = 0;

            @Override
            public void run(){
                if(ticks >= 30 || player == null){
                    this.cancel();
                    return;
                }
                ticks++;
                locations.add(player.getLocation());
            }
        }.runTaskTimer(Shame.getInstance(), 0, 2);
        new BukkitRunnable(){
            @Override
            public void run(){
                if(locations.isEmpty() || player == null){
                    this.cancel();
                    return;
                }
                player.teleport(locations.removeLast());
            }
        }.runTaskTimer(Shame.getInstance(), 60, 1);
    }
}
