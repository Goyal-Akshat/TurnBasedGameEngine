package org.akshat.boards;

import org.akshat.game.Board;
import org.akshat.game.Cell;
import org.akshat.game.Move;

public class TicTacToeBoard implements  Board {
    String cells[][] = new String[3][3];

    public String getSymbol(int row, int col) {
        return cells[row][col];
    }

    @Override
    public void move(Move move) {
        setCell(move.getCell(), move.getPlayer().symbol());
    }

    @Override
    public TicTacToeBoard copy() {
        TicTacToeBoard board = new TicTacToeBoard();
        for (int i = 0; i < 3; i++) {
            System.arraycopy(cells[i], 0, board.cells[i], 0, 3);
        }
        return board;
    }

    public void setCell(Cell cell, String symbol) {
        if(cells[cell.getRow()][cell.getCol()]==null) {
            cells[cell.getRow()][cell.getCol()] = symbol;
        }
        else {
            System.out.println(this);
            throw new IllegalArgumentException(cell.getRow() + " " + cell.getCol());
        }
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
