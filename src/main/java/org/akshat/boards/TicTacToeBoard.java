package org.akshat.boards;

import org.akshat.game.Board;
import org.akshat.game.Cell;
import org.akshat.game.Move;

public class TicTacToeBoard extends Board {
    String cells[][] = new String[3][3];

    public String getCell(int row, int col) {
        return cells[row][col];
    }

    @Override
    public void move(Move move) {
        setCell(move.getCell(), move.getPlayer().symbol());
    }

    public void setCell(Cell cell, String symbol) {
        cells[cell.getRow()][cell.getCol()] = symbol;
    }

    @Override
    public String toString() {
        String board = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board += (cells[i][j]!=null ? cells[i][j]:"_") + " ";
            }
            board += "\n";
        }
        return board;
    }
}
