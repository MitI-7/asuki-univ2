import game.Game;
import game.Game.Winner;
import player.Player;
import player.ai.RandomPlayer;
import player.ai.UCB1MonteCarloPlayer;
import player.ai.impl.AlphaBetaBoardScoreAIPlayer;
import player.ai.simple.SimpleAIPlayer;
import board.Board;
import board.Turn;

public class Main {
    public static void main(String[] args) {
        // Player blackPlayer = new RandomPlayer(Turn.BLACK);
        Player blackPlayer = new SimpleAIPlayer(Turn.BLACK);
        //Player blackPlayer = new SimpleMonteCarloPlayer(Turn.BLACK, 100);
        //Player blackPlayer = new UCB1MonteCarloPlayer(Turn.BLACK, 1000);
        //Player blackPlayer = new HumanPlayer(Turn.BLACK);
        //Player blackPlayer = new SimpleAI(Turn.BLACK);
        //Player blackPlayer = new MinMaxSimpleAI(Turn.BLACK, 5);
        //Player blackPlayer = new AlphaBetaSimpleAI(Turn.BLACK, 5);
        //Player whitePlayer = new SimpleAI(Turn.WHITE);
        //Player whitePlayer = new SimpleMonteCarloPlayer(Turn.WHITE, 100);
        Player whitePlayer = new UCB1MonteCarloPlayer(Turn.WHITE, 100);
        //Player whitePlayer = new MinMaxSimpleAI(Turn.WHITE, 5);
        //Player whitePlayer = new NegaMaxSimpleAI(Turn.WHITE, 5);
        //Player whitePlayer = new AlphaBetaBoardScoreAIPlayer(Turn.WHITE, 5);
        //Player whitePlayer = new AlphaBetaEvaluationSimpleWithCompleteReadingAI(Turn.WHITE, 5, 15);
        //Player whitePlayer = new NegaScoutEvaluationSimpleAI(Turn.WHITE, 5);
        //Player whitePlayer = new NegaScoutEvaluationSimpleWithCompleteReadingAI(Turn.WHITE, 5, 15);
        //Player whitePlayer = new TranpositionEvaluationSimpleAI(Turn.WHITE, 5);
        //Player whitePlayer = new TranpositionEvaluationCompleteReadingSimpleAI(Turn.WHITE, 5, 15);

        Game game = new Game();
        Board board = new Board();
        board.setup();
        Winner winner = game.play(blackPlayer, whitePlayer, board, Turn.BLACK, true);

        System.out.println("BLACK: " + blackPlayer.getClass().getSimpleName());
        System.out.println("WHITE: " + whitePlayer.getClass().getSimpleName());
        System.out.println("Winner: " + winner);
    }

}
