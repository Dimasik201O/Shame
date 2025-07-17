package org.dimasik.shame.fakelags.trolling.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerHeldItemChange;
import org.dimasik.shame.fakelags.trolling.AbstractTrolling;
import org.dimasik.shame.fakelags.trolling.TrollingType;

import java.util.Random;

public final class ItemsTrolling extends AbstractTrolling {

    public ItemsTrolling() {
        super(TrollingType.SWAP_ITEMS);
    }

    @Override
    public void packetReceive(PacketReceiveEvent e) {
        final User user = e.getUser();
        final Random random = new Random();
        final WrapperPlayServerHeldItemChange packet = new WrapperPlayServerHeldItemChange(random.nextInt(9));
        user.sendPacket(packet);
    }

    @Override
    public void packetSend(PacketSendEvent e) {

    }

}
