package machineCoding.tictactoe.strategies.botPlayingStrategies;

import machineCoding.tictactoe.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategyForDifficulty(BotDifficultyLevel botDifficultyLevel){
        return new EasyBotPlayingStrategy();
//        return switch (botDifficultyLevel){
//            case EASY -> new EasyBotPlayingStrategy();
//            case MEDIUM -> new MediumBotPlayingStrategy();
//            case HARD -> new HardBotPlayingStrategy();
//        };
    }
}
