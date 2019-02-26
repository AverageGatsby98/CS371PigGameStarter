package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

import java.util.Random;

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    private PigGameState gameState;

    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        this.gameState = new PigGameState();

    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if(this.gameState.getPlayerId() == playerIdx) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof PigRollAction){
            double rand = Math.random();

            if (rand < 1.0/6.0) {
                gameState.setDieValue(1);
                gameState.setHoldTotal(0);
                gameState.setPlayerId((gameState.getPlayerId()+1)%2);
                return true;
            } else if (rand < 2.0/6.0) {
                gameState.setDieValue(2);
            } else if (rand < 3.0/6.0) {
                gameState.setDieValue(3);
            } else if (rand < 4.0/6.0) {
                gameState.setDieValue(4);
            } else if (rand < 5.0 / 6.0) {
                gameState.setDieValue(5);
            } else {
                gameState.setDieValue(6);
            }
            gameState.setHoldTotal(gameState.getHoldTotal()+gameState.getDieValue());
            return true;
        }
        else if (action instanceof PigHoldAction){
          gameState.setPlayerScores(gameState.getHoldTotal() + gameState.getPlayerScore(gameState.getPlayerId()), gameState.getPlayerId());
          gameState.setHoldTotal(0);
          gameState.setPlayerId((gameState.getPlayerId()+1)%2);
          return true;
        }
        else{
          return false;
        }
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new PigGameState(this.gameState));
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        if(gameState.getPlayerScore(0) >= 50){
            return "player 0 won with:"+gameState.getPlayerScore(0);
        }
        else if(gameState.getPlayerScore(1) >= 50){
            return "player 1 won with: "+gameState.getPlayerScore(1);
        }
        return null;
    }

}// class PigLocalGame
