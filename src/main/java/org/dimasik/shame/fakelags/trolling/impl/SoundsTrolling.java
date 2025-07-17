package org.dimasik.shame.fakelags.trolling.impl;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.protocol.sound.SoundCategory;
import com.github.retrooper.packetevents.util.Vector3i;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSoundEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.dimasik.shame.fakelags.trolling.AbstractTrolling;
import org.dimasik.shame.fakelags.trolling.TrollingType;

import java.util.Random;

public final class SoundsTrolling extends AbstractTrolling {

    public SoundsTrolling() {
        super(TrollingType.SPAM_SOUNDS);
    }

    @Override
    public void packetReceive(PacketReceiveEvent e) {
        final User user = e.getUser();
        final Random random = new Random();
        final Location playerLocation = ((Player) e.getPlayer()).getLocation();
        final WrapperPlayServerSoundEffect packet = new WrapperPlayServerSoundEffect(Sound.values()[random.nextInt(Sound.values().length)].ordinal(), SoundCategory.values()[random.nextInt(SoundCategory.values().length)], new Vector3i(playerLocation.getBlockX(), playerLocation.getBlockY(), playerLocation.getBlockZ()), 100, 100);
        user.sendPacket(packet);
    }

    @Override
    public void packetSend(PacketSendEvent e) {

    }

}
