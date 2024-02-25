package machineCoding.tictactoe.models;

import java.awt.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    private PlayerType playerType;

    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Cell makeMove(Board board){//for human
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter row ");
        int row = scanner.nextInt();

        System.out.println("enter col");
        int col = scanner.nextInt();

        return new Cell(row, col);
    }

}
