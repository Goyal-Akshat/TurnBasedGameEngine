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
    AIEngine   aiEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup(){
        gameEngine = new GameEngine();
        aiEngine   = new AIEngine();
        ruleEngine = new RuleEngine();
    }

    public void playGame(int[][]moves, Board board) {
        int row,col;
        int next=0;
        while(!ruleEngine.getState(board).isOver()) {
            Player computer = new Player("O"),human = new Player("X");
            row = moves[next][0];
            col = moves[next][1];
            next++;
            Move oppMove = new Move(new Cell(row, col),human);
            gameEngine.move(board, oppMove);
            if(!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
        }
    }

    @Test
    public void checkForRowWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{1,0},{1,1},{1,2}};
        playGame(moves,board);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertTrue(ruleEngine.getState(board).getWinner().equals("X"));
    }

    @Test
    public void checkForColumnWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{0,0},{0,1},{0,2}};
        playGame(moves,board);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertTrue(ruleEngine.getState(board).getWinner().equals("X"));
    }

    @Test
    public void checkForDiagWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{0,0},{1,1},{2,2}};
        playGame(moves,board);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertTrue(ruleEngine.getState(board).getWinner().equals("X"));
    }

    @Test
    public void checkForRevDiagWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{0,2},{1,1},{2,0}};
        playGame(moves,board);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertTrue(ruleEngine.getState(board).getWinner().equals("X"));
    }

    @Test
    public void checkForComputerWin(){
        Board board = gameEngine.start("TicTacToe");
        int[][] moves = new int[][]{{1,0},{1,1},{2,2}};
        playGame(moves,board);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertTrue(ruleEngine.getState(board).getWinner().equals("O"));
    }
}
