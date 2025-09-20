package org.akshat.api;

import org.akshat.game.Board;
import org.akshat.game.GameState;

import java.util.function.Function;

public class Rule<T extends Board> {
    Function<T, GameState> condition;

    public Rule(Function<T, GameState> condition) {
        this.condition = condition;
    }
}
