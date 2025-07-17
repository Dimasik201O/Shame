package org.dimasik.shame.fakelags.trolling.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import org.dimasik.shame.fakelags.trolling.AbstractTrolling;
import org.dimasik.shame.fakelags.trolling.TrollingType;

public final class AllTrolling extends AbstractTrolling {

    public AllTrolling() {
        super(TrollingType.ALL_CANCEL);
    }

    @Override
    public void packetReceive(PacketReceiveEvent e) {
        if (Math.random() < 25 / 100.0) {
            e.setCancelled(true);
        }
    }

    @Override
    public void packetSend(PacketSendEvent e) {

    }

}
