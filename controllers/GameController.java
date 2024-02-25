package machineCoding.tictactoe.controllers;

import machineCoding.tictactoe.models.Board;
import machineCoding.tictactoe.models.Game;
import machineCoding.tictactoe.models.GameStatus;
import machineCoding.tictactoe.models.Player;
import machineCoding.tictactoe.strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game createGame(List<Player>players, List<WinningStrategy>winningStrategies, Board board){
        return Game.getBuilder(players, winningStrategies, board)
                .setPlayers(players)
                .setBoard(board)
                .setStrategy(winningStrategies)
                .Build();
    }
    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }
    public void printBoard(Game game){
        game.printBoard();
    }
    public int getCurrPlayerTurn(Game game){
        return 0;
    }
    public boolean validateMove(Game game){
        return false;
    }
    public void makeMove(Game game){
        game.makeMove();
    }

    public void undo(Game game) {
        game.undo();
    }
}

