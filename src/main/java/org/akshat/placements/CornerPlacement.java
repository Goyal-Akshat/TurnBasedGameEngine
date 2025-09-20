package org.akshat.placements;

import org.akshat.boards.TicTacToeBoard;
import org.akshat.game.Cell;
import org.akshat.game.Player;
import org.akshat.utils.Utils;

import java.util.Optional;

public class CornerPlacement implements Placement {

    private static CornerPlacement cornerPlacement;

    private CornerPlacement() {
    }

    public static Placement get() {
        cornerPlacement = (CornerPlacement) Utils.getIfNull(cornerPlacement, CornerPlacement::new);
        return cornerPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell corner = null;
        int corners[][] = new int[][]{{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for (int i = 0; i < 4; i++) {
            if (board.getSymbol(corners[i][0], corners[i][1]) == null) {
                corner = new Cell(corners[i][0], corners[i][1]);
            }
        }
        return Optional.ofNullable(corner);
    }

    @Override
    public Placement next() {
        return null;
    }
}
