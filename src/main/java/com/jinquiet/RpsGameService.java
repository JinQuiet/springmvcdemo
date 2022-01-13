package com.jinquiet;

import java.util.Arrays;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RpsGameService {

    private static Random d3Dice = new Random();

    private static int playMatrix[][] = 
    {
        {Rps.ROCK.ordinal(), Rps.ROCK.ordinal(), GameResult.DRAW.ordinal()},
        {Rps.ROCK.ordinal(), Rps.PAPER.ordinal(), GameResult.LOSS.ordinal()},
        {Rps.ROCK.ordinal(), Rps.SCISSORS.ordinal(), GameResult.VICTORY.ordinal()},

        {Rps.PAPER.ordinal(), Rps.ROCK.ordinal(), GameResult.DRAW.ordinal()},
        {Rps.PAPER.ordinal(), Rps.PAPER.ordinal(), GameResult.VICTORY.ordinal()},
        {Rps.PAPER.ordinal(), Rps.SCISSORS.ordinal(), GameResult.LOSS.ordinal()},

        {Rps.SCISSORS.ordinal(), Rps.ROCK.ordinal(), GameResult.LOSS.ordinal()},
        {Rps.SCISSORS.ordinal(), Rps.PAPER.ordinal(), GameResult.VICTORY.ordinal()},
        {Rps.SCISSORS.ordinal(), Rps.SCISSORS.ordinal(), GameResult.DRAW.ordinal()},
    };


    public RpsGameModel play(String playerAction) {

        RpsGameModel rpsGameModel = new RpsGameModel();

        if (!checkPlayerAction(playerAction)) return rpsGameModel;

        int diceRoll  = d3Dice.ints(0, 3).findFirst().getAsInt();

        String opponentAction = Rps.values()[diceRoll].name();

        int playerActionIndex = Rps.valueOf(playerAction).ordinal();
        int opponentActionIndex = Rps.valueOf(opponentAction).ordinal();

        int gameResultIndex = Arrays.stream(playMatrix)
                                        .filter(e -> (e[0] == playerActionIndex && e[1] == opponentActionIndex))
                                        .findFirst()
                                        .get()[2];

        

            rpsGameModel.setPlayerAction(playerAction);                                        
            rpsGameModel.setOpponentAction(opponentAction);
            rpsGameModel.setGameResult(GameResult.values()[gameResultIndex].name());
                                        
        return rpsGameModel;
    }

    private boolean checkPlayerAction(String action) {
        long actionCount = Arrays.stream(Rps.values()).filter(e -> e.name().equals(action)).count();

        return (actionCount == 1) ? true: false ;
    }
    
}
