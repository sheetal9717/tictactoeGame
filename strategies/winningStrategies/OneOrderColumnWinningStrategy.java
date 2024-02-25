package machineCoding.tictactoe.strategies.winningStrategies;

import machineCoding.tictactoe.models.Board;
import machineCoding.tictactoe.models.Move;
import machineCoding.tictactoe.models.Player;
import machineCoding.tictactoe.models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OneOrderColumnWinningStrategy implements WinningStrategy{
    private List<Map<Symbol, Integer>> colMap;

    public OneOrderColumnWinningStrategy(List<Player>players, int size){
        colMap = new ArrayList<>();
        for(int i=0; i<size; i++){
            colMap.add(new HashMap<>());
            for(Player player : players){
                colMap.get(i).put(player.getSymbol(), 0);
            }
        }
    }
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getColumn();
        Symbol symbol = move.getSymbol();

        colMap.get(col).put(symbol, 1 + colMap.get(col).get(symbol));

        System.out.println("col "+ col +" symbol "+ symbol.getaChar() + " count "+ colMap.get(col).get(symbol));
        if(colMap.get(col).get(move.getSymbol()) == board.getDimensions())
            return true;
        return false;
    }

    @Override
    public void makeUndo(Move move, Board board) {
        int col = move.getCell().getColumn();
        Symbol symbol = move.getSymbol();

        colMap.get(col).put(symbol, colMap.get(col).get(symbol) - 1);
    }
}
