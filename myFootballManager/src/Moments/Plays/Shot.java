package Moments.Plays;

import Player.*;
import Team.*;

/**
 * This class represents a Shot in football (soccer).
 * The comments are mainly written in English.
 * */
public class Shot extends Play {

    // Instance Variables
    private Player shootingPlayer;
    private Player goalkeeper;
    private ConsequencesAttackingPlay consequence;

    // Constructors

    /** The "Standard" constructor creates a (useful) Shot, given all it's attributes. */
    public Shot(int minute, int extraTime, boolean home_or_away, Player shootingPlayer, Player goalkeeper){
        super(minute,extraTime,home_or_away);
        this.shootingPlayer = shootingPlayer.clone();
        this.goalkeeper = goalkeeper.clone();
        consequence = randomAttackingPlay( 0.35, 0.35, 0.30
                , this.shootingPlayer.getOverall() - this.goalkeeper.getOverall());
    }

    /** The "Special" constructor creates a (useful) Shot, and founds the players from the teams. */
    public Shot(int minute, int extraTime, boolean home_or_away, Team attackingTeam, Team defensiveTeam) throws MissingPlayer {
        super(minute,extraTime,home_or_away);
        this.shootingPlayer = chosePlayer(attackingTeam,0,0.06,0.09,0.15,0.30,0.40);
        this.goalkeeper = defensiveTeam.getGoalkeeper();
        consequence = randomAttackingPlay( 0.35, 0.35, 0.30
                , this.shootingPlayer.getOverall() - this.goalkeeper.getOverall());
    }

    /** The "copy paste" constructor creates a Shot, given another Shot. */
    public Shot(Shot shot){
        super(shot);
        this.shootingPlayer = shot.shootingPlayer.clone();
        this.goalkeeper = shot.goalkeeper.clone();
        this.consequence = shot.consequence;
    }

    //Getters and Setters

    public Player getShootingPlayer() { return shootingPlayer.clone(); }
    public void setShootingPlayer(Player shootingPlayer) { this.shootingPlayer = shootingPlayer.clone(); }
    public Player getGoalkeeper() { return goalkeeper.clone(); }
    public void setGoalkeeper(Player goalkeeper) { this.goalkeeper = goalkeeper.clone(); }
    public ConsequencesAttackingPlay getConsequence() { return consequence; }
    public void setConsequence(ConsequencesAttackingPlay consequence) { this.consequence = consequence; }

    // Public Methods

    /** Was there a Goal ? */
    public boolean isGoal(){ return consequence == ConsequencesAttackingPlay.Goal; }

    /** Basic clone method */
    public Shot clone(){
        return new Shot(this);
    }

    /** Basic toString method */
    public String toString(){
        String sb = super.baseString();
        if (consequence == ConsequencesAttackingPlay.Goal) sb += "GOAL! Amazing strike from " +
                this.shootingPlayer.getName() + "!";
        else if (consequence == ConsequencesAttackingPlay.Missed) sb += "Missed strike by " +
                this.shootingPlayer.getName() + ".";
        else sb +=  this.goalkeeper.getName() + " saved a great strike by " + this.shootingPlayer.getName() + "!";
        return sb;
    }
}
