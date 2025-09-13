package org.akshat.api;

import org.akshat.boards.TicTacToeBoard;
import org.akshat.game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    Map<String, List<Rule<TicTacToeBoard>>> ruleMap = new HashMap<>();

    public RuleEngine(){
        ruleMap.put(TicTacToeBoard.class.getName(),new ArrayList<>());
        ruleMap.get(TicTacToeBoard.class.getName()).add(new Rule<TicTacToeBoard>(board -> outerTraversal((i,j)->board.getSymbol(i,j))));
        ruleMap.get(TicTacToeBoard.class.getName()).add(new Rule<TicTacToeBoard>(board -> outerTraversal((i,j)->board.getSymbol(j,i))));
        ruleMap.get(TicTacToeBoard.class.getName()).add(new Rule<TicTacToeBoard>(board -> traverse((i) -> board.getSymbol(i,i))));
        ruleMap.get(TicTacToeBoard.class.getName()).add(new Rule<TicTacToeBoard>(board -> traverse((i) -> board.getSymbol(i,2-i))));
        ruleMap.get(TicTacToeBoard.class.getName()).add(new Rule<TicTacToeBoard>(board -> {
            int countOfFilledCells = 0;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(board.getSymbol(i, j) != null) {
                        countOfFilledCells++;
                    }
                }
            }
            if(countOfFilledCells == 9) {
                return new GameState(true, "-");
            }
            return new GameState(false, "-");
        }));
    }
    public GameInfo getInfo(Board board){
        if(board instanceof TicTacToeBoard){
            GameState gameState = getState(board);
            final String[] players = new String[]{"X","O"};
            for(int playerSymbol=0;playerSymbol<2;playerSymbol++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Board b = board.copy();
                        Player player = new Player(players[playerSymbol]);
                        b.move(new Move(new Cell(i, j), player));
                        boolean canStillWin = false;
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                Board b1 = b.copy();
                                b1.move(new Move(new Cell(k, l), player.flip()));
                                if (getState(b1).getWinner().equals(player.flip().symbol())) {
                                    canStillWin = true;
                                    break;
                                }
                            }
                            if (canStillWin) break;
                        }
                        if (canStillWin) {
                            return new GameInfoBuilder()
                                    .isOver(gameState.isOver())
                                    .winner(gameState.getWinner())
                                    .hasFork(true)
                                    .player(player)
                                    .build();
                        }
                    }
                }
            }
            return new GameInfoBuilder().isOver(gameState.isOver())
                    .winner(gameState.getWinner())
                    .build();
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public GameState getState(Board board) {
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard board1 = (TicTacToeBoard) board;
            List<Rule<TicTacToeBoard>> rules = ruleMap.get(TicTacToeBoard.class.getName());
            for(Rule<TicTacToeBoard> r:rules){
                GameState gameState = r.condition.apply(board1);
                if(gameState.isOver()){
                    return gameState;
                }
            }
            return new GameState(false, "");
        }
        else {
            return new GameState(false, "");
        }
    }
    private GameState outerTraversal(BiFunction<Integer,Integer,String> next){
        GameState result = new GameState(false,"-");
        for(int i=0;i<3;i++){
            final int ii = i;
            GameState traversal = traverse(j->next.apply(ii,j));
            if(traversal.isOver()){
                result = traversal;
                break;
            }
        }
        return result;
    }

    private GameState traverse(Function<Integer,String> traversal){
        GameState result = new GameState(false,"-");
        boolean possibleStreak = true;
        for(int j=0;j<3;j++){
            if(traversal.apply(j)==null || !traversal.apply(0).equals(traversal.apply(j))){
                possibleStreak = false;
                break;
            }
        }
        if(possibleStreak){
            result= new GameState(true,traversal.apply(0));
        }
        return result;
    }
}

class Rule<T extends Board>{
    Function<T,GameState> condition;

    public Rule(Function<T,GameState> condition){
        this.condition = condition;
    }
}