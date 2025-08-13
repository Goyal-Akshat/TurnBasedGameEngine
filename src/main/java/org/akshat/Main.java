package org.akshat;

import org.akshat.api.AIEngine;
import org.akshat.api.RuleEngine;
import org.akshat.game.Board;
import org.akshat.game.Cell;
import org.akshat.game.Move;
import org.akshat.game.Player;
import org.akshat.api.GameEngine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine   = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        Board board = gameEngine.start("TicTacToe");
        int row, col;
        Scanner scanner = new Scanner(System.in);
        while (!ruleEngine.getState(board).isOver()) {
            Player computer = new Player("O"),human = new Player("X");
            System.out.println("Make your move");
            System.out.println(board);
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move oppMove = new Move(new Cell(row, col),human);
            gameEngine.move(board, oppMove);
            System.out.println(board);
            if(!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
        }

        System.out.println("Game Result: " + ruleEngine.getState(board).getWinner());
    }
}
