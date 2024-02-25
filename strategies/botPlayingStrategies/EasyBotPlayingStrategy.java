package machineCoding.tictactoe.strategies.botPlayingStrategies;

import machineCoding.tictactoe.models.Board;
import machineCoding.tictactoe.models.Cell;
import machineCoding.tictactoe.models.CellStatus;


import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{

    @Override
    public Cell makeMove(Board board) {
        //return first empty cell
        for(List<Cell>row : board.getBoard()){
            for(Cell cell : row){
                if(cell.getCellStatus().equals(CellStatus.EMPTY))
                    return cell;
            }
        }
        return null;
    }
}
