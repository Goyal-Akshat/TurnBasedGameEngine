package org.akshat.api;

import org.akshat.boards.TicTacToeBoard;
import org.akshat.game.*;
import org.akshat.placements.ForkPlacement;
import org.akshat.placements.Placement;

import java.util.Optional;

public class AIEngine {
    RuleEngine ruleEngine = new RuleEngine();

    public Move suggestMove(Player player,Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            Cell suggestion;
            int threshold = 3;
            if(countMoves(board1)<threshold){
                suggestion = getBasicMove(board1);
            }
            else if(countMoves(board1)>threshold+1){
                suggestion = getCellToPlay(player,board1);
            }
            else{
                suggestion = getOptimalMove(player,board1);
            }
            if(suggestion!=null)return new Move(suggestion,player);
            throw new IllegalArgumentException();
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    private Cell getOptimalMove(Player player,TicTacToeBoard board){
        Placement placement = ForkPlacement.get();
        while(placement.next()!=null){
            Optional<Cell> cell = placement.place(board,player);
            if(cell.isPresent())return cell.get();
            placement = placement.next();
        }
        return null;
    }
    private Cell getCellToPlay(Player player,TicTacToeBoard board){
        Cell best = offence(player,board);
        if(best!=null)return best;
        best = defence(player,board);
        if(best!=null)return best;
        return getBasicMove(board);
    }

    private Cell offence(Player player,TicTacToeBoard board){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getSymbol(i,j)==null){
                    Move move = new Move(new Cell(i,j),player);
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()) {
                        return move.getCell();
                    }
                }
            }
        }
        return null;
    }

    private Cell defence(Player player,TicTacToeBoard board){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getSymbol(i,j)==null){
                    Move move = new Move(new Cell(i,j),player.flip());
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()) {
                        return new Cell(i,j);
                    }
                }
            }
        }
        return null;
    }


    private int countMoves(TicTacToeBoard board){
        int count=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getSymbol(i,j)!=null){
                    count++;
                }
            }
        }
        return count;
    }

    private static Cell getBasicMove(TicTacToeBoard board1){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board1.getSymbol(i,j)==null){
                    return new Cell(i,j);
                }
            }
        }
        return null;
    }
}
