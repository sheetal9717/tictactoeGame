package machineCoding.tictactoe.models;

public class Move {
    private Cell cell;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    private Symbol symbol;

    public Move(Cell cell, Symbol symbol) {
        this.cell = cell;
        this.symbol = symbol;
    }
}

