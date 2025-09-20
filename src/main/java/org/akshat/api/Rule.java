package org.akshat.api;

import org.akshat.game.CellBoard;
import org.akshat.game.GameState;

import java.util.function.Function;

public class Rule {
    Function<CellBoard, GameState> condition;

    public Rule(Function<CellBoard, GameState> condition) {
        this.condition = condition;
    }
}
