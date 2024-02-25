package machineCoding.tictactoe.strategies.winningStrategies;

import machineCoding.tictactoe.models.Board;
import machineCoding.tictactoe.models.Move;
import machineCoding.tictactoe.models.Player;
import machineCoding.tictactoe.models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OneOrderDiagonalWinningStrategy implements WinningStrategy{
    private Map<Symbol, Integer> leftDiagonal;
    private Map<Symbol, Integer> rightDiagonal;

    public OneOrderDiagonalWinningStrategy(List<Player>players){
        leftDiagonal = new HashMap<>();
        rightDiagonal = new HashMap<>();
        for(Player player : players){
            leftDiagonal.put(player.getSymbol(), 0);
            rightDiagonal.put(player.getSymbol(),0);
        }
    }
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        if(row == col)
            leftDiagonal.put(move.getSymbol(), 1+leftDiagonal.get(move.getSymbol()));
        if(row+col == board.getDimensions()-1)
            rightDiagonal.put(move.getSymbol(), 1+rightDiagonal.get(move.getSymbol()));

        System.out.println("leftdiagonal symbol "+ move.getSymbol().getaChar() + " count "+ leftDiagonal.get(move.getSymbol()));
        System.out.println("rightdiagonal symbol "+ move.getSymbol().getaChar() + " count "+ rightDiagonal.get(move.getSymbol()));

        if(leftDiagonal.get(move.getSymbol()) == board.getDimensions() || rightDiagonal.get(move.getSymbol()) == board.getDimensions())
            return true;
        return false;
    }

    @Override
    public void makeUndo(Move move, Board board) {
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        if(row == col)
            leftDiagonal.put(move.getSymbol(), leftDiagonal.get(move.getSymbol())-1);
        if(row+col == board.getDimensions()-1)
            rightDiagonal.put(move.getSymbol(), rightDiagonal.get(move.getSymbol())-1);

    }
}
