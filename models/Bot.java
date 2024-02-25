package machineCoding.tictactoe.models;

import machineCoding.tictactoe.strategies.botPlayingStrategies.BotPlayingStrategyFactory;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(String name, Symbol symbol) {//if parent class has a type, 1) type should be enum 2) child class should initialise type from enum
        super(name, symbol, PlayerType.BOT);
    }

    @Override
    public Cell makeMove(Board board) {//make move based on bot difficulty level
        return BotPlayingStrategyFactory.getBotPlayingStrategyForDifficulty(BotDifficultyLevel.EASY).makeMove(board);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

}
