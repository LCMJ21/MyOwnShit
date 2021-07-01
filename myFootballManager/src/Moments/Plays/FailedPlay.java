package Moments.Plays;

import Player.Player;
import Team.*;

/**
 * This class represents a Freekick in football (soccer).
 * The comments are mainly written in English.
 * */
public class FailedPlay extends Play {

    // Instance Variables
    private Player attackingPlayer;
    private Player defensivePlayer;
    private Fail consequence;

    // Constructors

    /** The "Standard" constructor creates a (useful) FailedPlay, given all it's attributes. */
    public FailedPlay(int minute, int extraTime, boolean home_or_away, Player attackingPlayer, Player defensivePlayer){
        super(minute,extraTime,home_or_away);
        this.attackingPlayer = attackingPlayer.clone();
        this.defensivePlayer = defensivePlayer.clone();
        double dif =  this.attackingPlayer.getOverall() - this.defensivePlayer.getOverall();
        consequence = randomFail( 0.35, 0.2, 0.45, dif);
    }

    /** The "Special" constructor creates a (useful) FailedPlay, and founds the players from the teams. */
    public FailedPlay(int minute, int extraTime, boolean home_or_away, Team attackingTeam, Team defensiveTeam) throws MissingPlayer {
        super(minute,extraTime,home_or_away);
        this.attackingPlayer = chosePlayer(attackingTeam,0,0.06,0.09,0.15,0.30,0.40);
        this.defensivePlayer = chosePlayer(defensiveTeam,0,0.40,0.30,0.15,0.09,0.06);
        double dif =  this.attackingPlayer.getOverall() - this.defensivePlayer.getOverall();
        consequence = randomFail( 0.35, 0.2, 0.45, dif);
    }

    /** The "copy paste" constructor creates a FailedPlay, given another FailedPlay. */
    public FailedPlay(FailedPlay failedPLay){
        super(failedPLay);
        this.attackingPlayer = failedPLay.attackingPlayer.clone();
        this.defensivePlayer = failedPLay.defensivePlayer.clone();
        this.consequence = failedPLay.consequence;
    }

    //Getters and Setters

    public Player getAttackingPlayer() { return attackingPlayer.clone(); }
    public void setAttackingPlayer(Player attackingPlayer) { this.attackingPlayer = attackingPlayer.clone(); }
    public Player getDefensivePlayer() { return defensivePlayer.clone(); }
    public void setDefensivePlayer(Player defensivePlayer) { this.defensivePlayer = defensivePlayer.clone(); }
    public Fail getConsequence() { return consequence; }
    public void setConsequence(Fail consequence) { this.consequence = consequence; }

    // Public Methods

    /** Was there a Goal ? */
    public boolean isGoal(){ return false; }

    /** Basic clone method */
    public FailedPlay clone(){
        return new FailedPlay(this);
    }

    /** Basic toString method */
    public String toString(){
        String sb = super.baseString();
        if (consequence == Fail.CounterAttack) sb += this.attackingPlayer.getName() + " lost the ball, "
                + this.defensivePlayer.getName() + " is going for a counter attack!";
        else if (consequence == Fail.Outside) sb += this.attackingPlayer.getName() + " was caught outside!";
        else sb += this.attackingPlayer.getName() + " lost the ball to " + this.defensivePlayer.getName() + ".";
        return sb;
    }
}
