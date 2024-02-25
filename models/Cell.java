package machineCoding.tictactoe.models;

public class Cell {
    private int row;
    private int column;
    private CellStatus cellStatus;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private Player player;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.cellStatus = CellStatus.EMPTY;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }
}
