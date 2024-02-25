package machineCoding.tictactoe.strategies.botPlayingStrategies;

import machineCoding.tictactoe.models.Board;
import machineCoding.tictactoe.models.Cell;
import machineCoding.tictactoe.models.Move;

public interface BotPlayingStrategy {
    public Cell makeMove(Board board);
}
