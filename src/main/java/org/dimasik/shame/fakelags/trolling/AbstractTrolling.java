package org.dimasik.shame.fakelags.trolling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dimasik.shame.fakelags.trolling.impl.*;

@Getter
@AllArgsConstructor
public abstract class AbstractTrolling implements ITrolling {

    private final TrollingType type;

    public static AbstractTrolling byType(TrollingType type) {
        switch (type) {
            case SPAM_SOUNDS -> { return new SoundsTrolling(); }
            case SWAP_ITEMS -> { return new ItemsTrolling(); }
            case FANTOM_BLOCKS -> { return new BlocksTrolling(); }
            case ENTITIES_NO_MOVE -> { return  new FreezeTrolling(); }
            default -> { return new AllTrolling(); }
        }
    }

}
