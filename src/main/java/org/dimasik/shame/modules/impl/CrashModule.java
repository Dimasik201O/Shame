package org.dimasik.shame.modules.impl;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.dimasik.shame.Shame;
import org.dimasik.shame.modules.Module;

public class CrashModule extends Module {
    public static void startCrash(Player player){
        Bukkit.getScheduler().runTaskAsynchronously(Shame.getInstance(), () -> {
            for (int i = 0; i < 10000; i++) {
                player.spawnParticle(Particle.FLASH, player.getLocation().clone().add(0, -0, 0), 10000);
            }
        });
    }
}
