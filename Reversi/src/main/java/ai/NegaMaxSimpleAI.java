package ai;

import player.Player;
import board.Board;
import board.Position;
import board.Stone;
import board.Turn;

public class NegaMaxSimpleAI implements Player {
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
    
    private final int MAX_DEPTH = 5;
    private Turn turn;
    
    public NegaMaxSimpleAI(Turn turn) {
        this.turn = turn;
    }
    
    @Override
    public Position play(Board board) {
        return eval(board, MAX_DEPTH, turn.stone(), 0).getPosition();
    }

    private EvalResult eval(Board board, int restDepth, Stone stone, int scoreSum) {
        if (restDepth == 0)
            return new EvalResult(-scoreSum, null);
        
        // 自分の番では、評価が最も大きくなるものを選ぶ。
        boolean didPlayed = false;
        int maxScore = -10000;
        Position p = null;
        for (int y = 1; y <= Board.HEIGHT; ++y) {
            for (int x = 1; x <= Board.WIDTH; ++x) {
                if (board.isPuttable(x, y, stone)) {
                    didPlayed = true;
                    Board b = new Board(board);
                    b.put(x, y, stone);
                    int score = -eval(b, restDepth - 1, stone.flip(), scoreSum + (stone == turn.stone() ? EVAL_VALUES[y-1][x-1] : -EVAL_VALUES[y-1][x-1])).getScore();
                    if (maxScore < score) {
                        maxScore = score;
                        p = new Position(x, y);
                    }
                }
            }
        }
        
        if (didPlayed) {
            assert (p != null);
            return new EvalResult(maxScore, p);
        } else {
            // 自分の番がパスだった場合は、-100 点しておく。
            int score = -eval(board, restDepth - 1, stone.flip(), scoreSum).getScore();
            return new EvalResult(score, null);
        }
    }
}
