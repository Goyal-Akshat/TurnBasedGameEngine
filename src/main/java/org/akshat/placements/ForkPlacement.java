package org.akshat.placements;

import org.akshat.boards.TicTacToeBoard;
import org.akshat.game.Cell;
import org.akshat.game.GameInfo;
import org.akshat.game.Player;
import org.akshat.utils.Utils;

import java.util.Optional;

public class ForkPlacement implements Placement {

    private static ForkPlacement forkPlacement;

    private ForkPlacement() {
    }

    public static Placement get() {
        forkPlacement = (ForkPlacement) Utils.getIfNull(forkPlacement, ForkPlacement::new);
        return forkPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        GameInfo gameInfo = ruleEngine.getInfo(board);
        Cell best = null;
        if (gameInfo.hasFork()) {
            best = gameInfo.getForkCell();
        }
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        return CenterPlacement.get();
    }
}
