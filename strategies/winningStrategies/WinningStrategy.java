package machineCoding.tictactoe.strategies.winningStrategies;

import machineCoding.tictactoe.models.Board;
import machineCoding.tictactoe.models.Move;

public interface WinningStrategy {
    public boolean checkWinner(Board board, Move move);

    void makeUndo(Move move, Board board);
}
