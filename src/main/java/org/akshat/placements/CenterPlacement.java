package org.akshat.placements;

import org.akshat.boards.TicTacToeBoard;
import org.akshat.game.Cell;
import org.akshat.game.Player;
import org.akshat.utils.Utils;

import java.util.Optional;

public class CenterPlacement implements Placement {

    private static CenterPlacement centerPlacement;

    private CenterPlacement() {
    }

    public static Placement get() {
        centerPlacement = (CenterPlacement) Utils.getIfNull(centerPlacement, CenterPlacement::new);
        return centerPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell center = null;
        if (board.getSymbol(1, 1) == null) {
            center = new Cell(1, 1);
        }
        return Optional.ofNullable(center);
    }

    @Override
    public Placement next() {
        return CornerPlacement.get();
    }
}
