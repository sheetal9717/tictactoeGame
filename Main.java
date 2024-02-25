package machineCoding.tictactoe;

import machineCoding.tictactoe.controllers.GameController;
import machineCoding.tictactoe.models.*;
import machineCoding.tictactoe.strategies.winningStrategies.OneOrderColumnWinningStrategy;
import machineCoding.tictactoe.strategies.winningStrategies.OneOrderDiagonalWinningStrategy;
import machineCoding.tictactoe.strategies.winningStrategies.OneOrderRowWinningStrategy;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        //create a game
        //while game status is in progress
        //print board state
        //  get next player
        //  if player is bot
        //      make move
        //  else if player is human
        //      take input from user
        //      validate move
        //      make move
        //          update board, turn, add move to movelist
        //check if move leads to game end
        // print result
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();
        System.out.println("Enter your name");
        String name = scanner.nextLine();

        List<Player>players = List.of(new Player(name, new Symbol(name.charAt(0)), PlayerType.HUMAN),
                new Bot("Computer", new Symbol('C')));
        int size = 3;
        Game game = gameController.createGame(
                players,
                List.of(new OneOrderRowWinningStrategy(players, size),
                        new OneOrderColumnWinningStrategy(players, size),
                        new OneOrderDiagonalWinningStrategy(players)),
                new Board(size)
        );

        System.out.println("---------Game Started---------");
        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            gameController.printBoard(game);
            System.out.println("Do you want to undo (Y/N)");
            String input = scanner.nextLine();

            if(input.equalsIgnoreCase("Y")){
                gameController.undo(game);
            }
            else{
                gameController.makeMove(game);
            }

        }
    }
}
