package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState {

    private int playerId;
    private int[] playerScores = new int[2];
    private int holdTotal;
    private int dieValue;

    public PigGameState(){
        this.playerId =0;
        this.playerScores = new int[]{0,0};
        this.holdTotal =0;
        this.dieValue = 0;

    }
    public PigGameState(PigGameState state){
        this.playerId =state.playerId;
        this.playerScores = new int[] {state.playerScores[0], state.playerScores[1]};
        this.holdTotal =state.holdTotal;
        this.dieValue = state.dieValue;
    }

    public int getPlayerId() {
        return playerId;
    }



    public int getHoldTotal() {
        return holdTotal;
    }

    public int getDieValue() {
        return dieValue;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerScore(int playerId) {
        return playerScores[playerId];
    }

    public void setPlayerScores(int playerScore, int playerId){
        this.playerScores[playerId] = playerScore;
    }

    public void setHoldTotal(int holdTotal) {
        this.holdTotal = holdTotal;
    }

    public void setDieValue(int dieValue) {
        this.dieValue = dieValue;
    }
}
