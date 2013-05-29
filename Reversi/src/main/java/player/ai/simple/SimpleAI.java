package player.ai.simple;

import player.ai.AIPlayer;
import board.Board;
import board.Position;
import board.Turn;

public class SimpleAI extends AIPlayer {
    private static final int[][] EVAL_VALUES = {
        { 100, -50, 35, 30, 30, 35, -50, 100 },
        { -50, -70, 10, 15, 15, 10, -70, -50 },
        {  35,  10, 20, 25, 25, 20,  10,  35 },
        {  30,  15, 25, 50, 50, 25,  15,  30 },
        {  30,  15, 25, 50, 50, 25,  15,  30 },
        {  35,  10, 20, 25, 25, 20,  10,  35 },
        { -50, -70, 10, 15, 15, 10, -70, -50 },
        { 100, -50, 35, 30, 30, 35, -50, 100 },
    };

    public SimpleAI(Turn turn) {
        super(turn);
    }

    @Override
    public Position play(Board board) {
        Position selectedPosition = null;
        int maxValue = -200;

        for (Position p : findPuttableHands(board, turn.stone())) {
            if (maxValue < EVAL_VALUES[p.y - 1][p.x - 1]) {
                maxValue = EVAL_VALUES[p.y - 1][p.x - 1];
                selectedPosition = p;
            }
        }

        assert (selectedPosition != null);
        return selectedPosition;
    }
}
