package Moments.Plays;

import Player.Player;
import Team.*;

/**
 * This class represents a TeamPlay in football (soccer).
 * The comments are mainly written in English.
 * */
public class TeamPlay extends Play {

    // Instance Variables
    private Player teamPlayer;
    private Player shootingPlayer;
    private Player defender;
    private ConsequencesAttackingPlay consequence;

    // Constructors

    /** The "Standard" constructor creates a (useful) TeamPlay, given all it's attributes. */
    public TeamPlay(int minute, int extraTime, boolean home_or_away, Player teamPlayer, Player shootingPlayer, Player defender){
        super(minute,extraTime,home_or_away);
        this.teamPlayer = teamPlayer.clone();
        this.shootingPlayer = shootingPlayer.clone();
        this.defender = defender.clone();
        double dif = ( ((this.shootingPlayer.getOverall() + this.teamPlayer.getOverall())/2.0) - this.defender.getOverall());
        consequence = randomAttackingPlay( 0.40, 0.35, 0.25, dif);
    }

    /** The "Special" constructor creates a (useful) TeamPlay, and founds the players from the teams. */
    public TeamPlay(int minute, int extraTime, boolean home_or_away, Team attackingTeam, Team defensiveTeam) throws MissingPlayer{
        super(minute,extraTime,home_or_away);
        this.teamPlayer = chosePlayer(attackingTeam,0,0.05,0.20,0.35,0.30,0.10);
        this.shootingPlayer = chosePlayer(attackingTeam,0,0.06,0.09,0.15,0.30,0.40);
        this.defender = chosePlayer(defensiveTeam,0.20,0.40,0.20,0.10,0.05,0.05);
        double dif = ( ((this.shootingPlayer.getOverall() + this.teamPlayer.getOverall())/2.0) - this.defender.getOverall());
        consequence = randomAttackingPlay( 0.40, 0.35, 0.25, dif);
    }

    /** The "copy paste" constructor creates a TeamPlay, given another TeamPlay. */
    public TeamPlay(TeamPlay teamPlay){
        super(teamPlay);
        this.teamPlayer = teamPlay.teamPlayer.clone();
        this.shootingPlayer = teamPlay.shootingPlayer.clone();
        this.defender = teamPlay.defender.clone();
        this.consequence = teamPlay.consequence;
    }

    //Getters and Setters

    public void setTeamPlayer(Player teamPlayer) { this.teamPlayer = teamPlayer; }
    public Player getTeamPlayer() { return teamPlayer.clone(); }
    public Player getShootingPlayer() { return shootingPlayer.clone(); }
    public void setShootingPlayer(Player shootingPlayer) { this.shootingPlayer = shootingPlayer.clone(); }
    public Player getDefender() { return defender; }
    public void setDefender(Player defender) { this.defender = defender; }
    public ConsequencesAttackingPlay getConsequence() { return consequence; }
    public void setConsequence(ConsequencesAttackingPlay consequence) { this.consequence = consequence; }

    // Public Methods

    /** Was there a Goal ? */
    public boolean isGoal(){ return consequence == ConsequencesAttackingPlay.Goal; }

    /** Basic clone method */
    public TeamPlay clone(){
        return new TeamPlay(this);
    }

    /** Basic toString method */
    public String toString(){
        String sb = super.baseString();
        if (consequence == ConsequencesAttackingPlay.Goal)  sb += "GOAL! A great assist from " + this.teamPlayer.getName()
                + ", for a great goal from " + this.shootingPlayer.getName() + ".";
        else if (consequence == ConsequencesAttackingPlay.Missed)  sb += "A cross from "
                + this.teamPlayer.getName() + " but no one gets to the ball.";
        else  sb += "A great cross from " + this.teamPlayer.getName() + ", amazing strike from "
                    + this.shootingPlayer.getName() + " but " + this.defender.getName() + " blocked it.";
        return sb;
    }
}