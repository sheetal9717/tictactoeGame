package machineCoding.tictactoe.strategies.winningStrategies;

import machineCoding.tictactoe.models.Board;
import machineCoding.tictactoe.models.Move;
import machineCoding.tictactoe.models.Player;
import machineCoding.tictactoe.models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OneOrderRowWinningStrategy implements WinningStrategy{
    private List<Map<Symbol, Integer>>rowMap;

    public OneOrderRowWinningStrategy(List<Player>players, int size){
        rowMap = new ArrayList<>();
        for(int i=0; i<size; i++){
            rowMap.add(new HashMap<>());
            for(Player player : players){
                rowMap.get(i).put(player.getSymbol(), 0);
            }
        }
    }
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getSymbol();
        rowMap.get(row).put(symbol, 1 + rowMap.get(row).get(symbol));//update updated cell

        System.out.println("row "+ row +" symbol "+ symbol.getaChar() + " count "+ rowMap.get(row).get(symbol));
        if(rowMap.get(row).get(move.getSymbol()) == board.getDimensions())
            return true;
        return false;
    }

    @Override
    public void makeUndo(Move move, Board board) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getSymbol();
        rowMap.get(row).put(symbol, rowMap.get(row).get(symbol) - 1);//update updated cell
    }

}
