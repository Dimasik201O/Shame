package org.dimasik.shame.fakelags.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.dimasik.shame.Shame;

public final class PlayerListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Shame.getTrollingManager().getTrollings().remove(e.getPlayer());
    }

}
