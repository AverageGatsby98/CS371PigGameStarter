package edu.up.cs301.pig;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    private PigGameState gameState;

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        if (!(info instanceof PigGameState)) return;

        this.gameState = (PigGameState)info;

        GameAction actionToTake = null;

        if (gameState.getPlayerId() != this.playerNum) {
            return;
        } else{
            double rand = Math.random();
            if (rand < 0.5) {
                actionToTake = new PigHoldAction(this);
            } else {
                actionToTake = new PigRollAction(this);
            }
        }
        game.sendAction(actionToTake);
    }//receiveInfo

}
