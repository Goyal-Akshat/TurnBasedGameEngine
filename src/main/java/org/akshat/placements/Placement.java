package org.akshat.placements;

import org.akshat.api.RuleEngine;
import org.akshat.boards.TicTacToeBoard;
import org.akshat.game.*;

import java.util.Optional;

public interface Placement {
    RuleEngine ruleEngine = new RuleEngine();
    Optional<Cell> place(TicTacToeBoard board, Player player);
    Placement next();
}

