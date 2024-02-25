package machineCoding.tictactoe.models;

import machineCoding.tictactoe.strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Player>players;
    private List<Move>moves;
    private List<WinningStrategy>winningStrategies;
    private Board board;
    private int currPlayerTurn;
    private GameStatus gameStatus;

    private Player winner;


    public static Builder getBuilder(List<Player> players, List<WinningStrategy> winningStrategy, Board board){
        return new Builder();
    }
    private Game(List<Player> players, List<WinningStrategy> winningStrategies, Board board) {
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.board = board;
        this.currPlayerTurn = 0;
        this.moves = new ArrayList<>();
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getCurrPlayerTurn() {
        return currPlayerTurn;
    }

    public void setCurrPlayerTurn(int currPlayerTurn) {
        this.currPlayerTurn = currPlayerTurn;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }


    public void makeMove() {
        //get cell from human and bot
        //validate cell
        //if validation doesn't fail make move
            //update board cell
            //add move to movelist
            //update cell status
            //check if move leads to game end(check if using winning strategy gives winner) or draw(if all cells are full)
        //update turn
        Player currPlayer = players.get(currPlayerTurn);
        Cell proposedCell = players.get(currPlayerTurn).makeMove(board);

        if(!validateCell(proposedCell)){
            System.out.println("Invalid row and column");
            return;
        }

        Cell cellInBoard = board.getBoard().get(proposedCell.getRow()).get(proposedCell.getColumn());
        cellInBoard.setCellStatus(CellStatus.FILLED);
        cellInBoard.setPlayer(currPlayer);



        Move move = new Move(cellInBoard, currPlayer.getSymbol());
        moves.add(move);

        if (checkGameWon(move, currPlayer)) return;

        if (checkGameDraw()) return;

        currPlayerTurn = (currPlayerTurn+1) % players.size();

    }

    private boolean checkGameDraw() {
        if(moves.size() == board.getDimensions() * board.getDimensions()){
            gameStatus = GameStatus.DRAW;
            System.out.println(" Game is draw");
            return true;
        }
        return false;
    }

    private boolean checkGameWon(Move move, Player currPlayer) {
        for(WinningStrategy winningStrategy : winningStrategies){

            if(winningStrategy.checkWinner(board, move)){
                winner = currPlayer;
                gameStatus = GameStatus.ENDED;

                System.out.println("winner is  " + winner.getName());
                System.out.println("---------Game Ended---------");

                return true;
            }
        }
        return false;
    }

    private boolean validateCell(Cell proposedCell) {
        int row = proposedCell.getRow();
        int col = proposedCell.getColumn();

        if(row < 0 || row >= board.getDimensions() || col<0 || col>= getBoard().getDimensions())
            return false;

        if(board.getBoard().get(row).get(col).getCellStatus().equals(CellStatus.FILLED))//check status of cell from board not from proposed cell
            return false;
        return true;
    }

    public void printBoard() {

        for(List<Cell>row : board.getBoard()){
            System.out.print("|");
            for(Cell cell : row){
                if(cell.getCellStatus().equals(CellStatus.EMPTY)){
                    System.out.print(" - |");
                }
                else{
                    System.out.print(" " +cell.getPlayer().getSymbol().getaChar()+" |");
                }
            }
            System.out.println();
        }


    }

    public void undo() {
        //get last move
        //remove cell from board
        //udpate strategy
        //check winner
        //check draw
        //update turn

        if(moves.isEmpty()){
            System.out.println("no move, Can't undo");
            return;
        }
        Move move = moves.getLast();
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellStatus(CellStatus.EMPTY);
        cell.setPlayer(null);

        for(WinningStrategy winningStrategy : winningStrategies){
            winningStrategy.makeUndo(move, board);
        }

        moves.remove(move);

        if (checkGameWon(move, cell.getPlayer())) return;

        if (checkGameDraw()) return;

        currPlayerTurn = (currPlayerTurn - 1 + players.size() ) % players.size();

    }


    public static class Builder{
        public List<Player>players;
        public Board board;
        public List<WinningStrategy> winningStrategies;

        public List<WinningStrategy> getStrategy() {
            return winningStrategies;
        }

        public Builder setStrategy(List<WinningStrategy> strategy) {
            this.winningStrategies = strategy;
            return this;
        }


        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Board getBoard() {
            return board;
        }

        public Builder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public Game Build(){
            if(!validation()){
                throw new RuntimeException("Validation failed");
            }
            return new Game(players,winningStrategies, board);
        }

        private boolean validation() {
            //check #player : n-1, should be >2
            //unique symbol for each player
            //only 1 bot is allowed
            if(players.size()<2)
                return false;
            if(players.size() != board.getDimensions()-1)
                return false;

            //check each player assigned with single char
            Set<Character>currSet = new HashSet<>();
            for(Player player : players){
                currSet.add(player.getSymbol().getaChar());
            }

            if(currSet.size() < players.size())
                return false;

            //only 1 bot is allowed
            int botCount = 0;
            for (Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT))
                    botCount++;
            }
            if(botCount > 1)
                return false;

            return true;
        }
    }
}
