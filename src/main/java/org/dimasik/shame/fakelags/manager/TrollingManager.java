package org.dimasik.shame.fakelags.manager;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.dimasik.shame.fakelags.trolling.AbstractTrolling;
import org.dimasik.shame.fakelags.trolling.TrollingType;

import java.util.HashMap;
import java.util.HashSet;

@Getter
public final class TrollingManager {

    private final HashMap<Player, HashSet<AbstractTrolling>> trollings;

    public TrollingManager() {
        this.trollings = new HashMap<>();
    }

    public void addTrolling(Player player, TrollingType type) {
        this.trollings.computeIfAbsent(player, p -> new HashSet<>())
                .add(AbstractTrolling.byType(type));
    }

    public void removeTrolling(Player player, TrollingType type) {
        HashSet<AbstractTrolling> trollingsPlayer = this.trollings.get(player);

        if (trollingsPlayer == null) {
            return;
        }

        trollingsPlayer.removeIf((trolling) -> trolling.getType() == type);
    }

    public boolean isAlreadyTrollingSetup(Player player, TrollingType type) {
        HashSet<AbstractTrolling> trollingsPlayer = this.trollings.get(player);

        if (trollingsPlayer == null) {
            return false;
        }

        return trollingsPlayer.stream()
                .filter((trolling) -> trolling.getType() == type)
                .findFirst()
                .orElse(null) != null;
    }

}
