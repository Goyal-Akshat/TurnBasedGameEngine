import org.akshat.api.AIEngine;
import org.akshat.api.GameEngine;
import org.akshat.api.RuleEngine;
import org.akshat.game.Board;
import org.akshat.game.Cell;
import org.akshat.game.Move;
import org.akshat.game.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GamePlayTest {
    GameEngine gameEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup(){
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
    }

    public void playGame(Board board,int[][]firstPlayerMoves,int[][] secondPlayerMoves ) {
        int row,col;
        int next=0;
        while(!ruleEngine.getState(board).isOver()) {
            Player first = new Player("X"),second = new Player("O");
            row = firstPlayerMoves[next][0];
            col = firstPlayerMoves[next][1];
            Move firstPlayerMove = new Move(new Cell(row, col),first);
            gameEngine.move(board, firstPlayerMove);
            if(!ruleEngine.getState(board).isOver()) {
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];
                Move secondPlayerMove = new Move(new Cell(sRow, sCol),second);
                gameEngine.move(board, secondPlayerMove);
            }
            next++;
        }
    }

    @Test
    public void checkForRowWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{1,0},{1,1},{1,2}};
        int[][] secondPlayerMoves = new int[][]{{0,0},{0,1},{0,2}};
        playGame(board,moves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertTrue(ruleEngine.getState(board).getWinner().equals("X"));
    }

    @Test
    public void checkForColumnWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{0,0},{1,0},{2,0}};
        int[][] secondPlayerMoves = new int[][]{{0,1},{0,2},{1,1}};
        playGame(board,moves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertTrue(ruleEngine.getState(board).getWinner().equals("X"));
    }

    @Test
    public void checkForDiagWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{0,0},{1,1},{2,2}};
        int[][] secondPlayerMoves = new int[][]{{0,1},{0,2},{1,0}};
        playGame(board,moves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertTrue(ruleEngine.getState(board).getWinner().equals("X"));
    }

    @Test
    public void checkForRevDiagWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{0,2},{1,1},{2,0}};
        int[][] secondPlayerMoves = new int[][]{{0,0},{0,1},{1,0}};
        playGame(board,moves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertTrue(ruleEngine.getState(board).getWinner().equals("X"));
    }

    @Test
    public void checkForComputerWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{1,0},{1,1},{2,2}};
        int[][] secondPlayerMoves = new int[][]{{0,0},{0,1},{0,2}};
        playGame(board,moves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertTrue(ruleEngine.getState(board).getWinner().equals("O"));
    }
}
