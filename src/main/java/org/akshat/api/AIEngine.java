package org.akshat.api;

import org.akshat.boards.TicTacToeBoard;
import org.akshat.game.Board;
import org.akshat.game.Cell;
import org.akshat.game.Move;
import org.akshat.game.Player;

public class AIEngine {
    public Move suggestMove(Player computer,Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            Move suggestion;
            int threshold = 3;
            if(countMoves(board1)<threshold){
                suggestion = getBasicMove(computer,board1);
            }
            else{
                suggestion = getSmartMove(computer,board1);
            }
            if(suggestion!=null)return suggestion;
            throw new IllegalArgumentException();
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    private Move getSmartMove(Player player,TicTacToeBoard board){
        RuleEngine ruleEngine = new RuleEngine();
        //Attacking moves
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getSymbol(i,j)==null){
                    Move move = new Move(new Cell(i,j),player);
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()) {
                        return move;
                    }
                }
            }
        }
        //Defensive moves
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getSymbol(i,j)==null){
                    Move move = new Move(new Cell(i,j),player.flip());
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()) {
                        return new Move(new Cell(i,j),player);
                    }
                }
            }
        }
        return getBasicMove(player,board);
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

    private static Move getBasicMove(Player computer,TicTacToeBoard board1){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board1.getSymbol(i,j)==null){
                    return new Move(new Cell(i,j),computer);
                }
            }
        }
        return null;
    }
}
