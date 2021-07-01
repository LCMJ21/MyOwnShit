package Moments.Plays;

import Player.Player;
import Team.*;

/**
 * This class represents a Corner in football (soccer).
 * The comments are mainly written in English.
 * */
public class Corner extends Play {

    // Instance Variables
    private Player cornerTaker;
    private Player areaPlayer;
    private Player defender;
    private ConsequencesAttackingPlay consequence;

    // Constructors

    /** The "Standard" constructor creates a (useful) Corner, given all it's attributes. */
    public Corner(int minute, int extraTime, boolean home_or_away, Player cornerTaker, Player areaPlayer, Player defender){
        super(minute,extraTime,home_or_away);
        this.cornerTaker = cornerTaker.clone();
        this.areaPlayer = areaPlayer.clone();
        this.defender = defender.clone();
        double dif =  ((this.areaPlayer.getOverall() + this.cornerTaker.getOneAttribute(Player.Corners))/2.0) - this.defender.getOverall();
        consequence = randomAttackingPlay( 0.4, 0.3, 0.3, dif);
    }

    /** The "Special" constructor creates a (useful) Corner, and founds the players from the teams. */
    public Corner(int minute, int extraTime, boolean home_or_away, Team attackingTeam, Team defensiveTeam) throws MissingPlayer {
        super(minute,extraTime,home_or_away);
        this.cornerTaker = attackingTeam.getCRTaker();
        this.areaPlayer = chosePlayer(attackingTeam,0,0.35,0.1,0.15,0.1,0.3);
        this.defender = chosePlayer(defensiveTeam,0.20,0.40,0.20,0.10,0.05,0.05);
        double dif =  ((this.areaPlayer.getOverall() + this.cornerTaker.getOneAttribute(Player.Corners))/2.0) - this.defender.getOverall();
        consequence = randomAttackingPlay( 0.4, 0.3, 0.3, dif);
    }

    /** The "copy paste" constructor creates a Corner, given another Corner. */
    public Corner(Corner teamPlay){
        super(teamPlay);
        this.cornerTaker = teamPlay.cornerTaker.clone();
        this.areaPlayer = teamPlay.areaPlayer.clone();
        this.defender = teamPlay.defender.clone();
        this.consequence = teamPlay.consequence;
    }

    //Getters and Setters

    public void setCornerTaker(Player cornerTaker) { this.cornerTaker = cornerTaker; }
    public Player getCornerTaker() { return cornerTaker.clone(); }
    public Player getAreaPlayer() { return areaPlayer.clone(); }
    public void setAreaPlayer(Player areaPlayer) { this.areaPlayer = areaPlayer.clone(); }
    public Player getDefender() { return defender; }
    public void setDefender(Player defender) { this.defender = defender; }
    public ConsequencesAttackingPlay getConsequence() { return consequence; }
    public void setConsequence(ConsequencesAttackingPlay consequence) { this.consequence = consequence; }

    // Public Methods

    /** Was there a Goal ? */
    public boolean isGoal(){ return consequence == ConsequencesAttackingPlay.Goal; }

    /** Basic clone method */
    public Corner clone(){
        return new Corner(this);
    }

    /** Basic toString method */
    public String toString(){
        String s = super.baseString();
        if (consequence == ConsequencesAttackingPlay.Goal) s += "GOAL! A great corner from " + this.cornerTaker.getName()
                + ", amazing header from " + this.areaPlayer.getName();
        else if (consequence == ConsequencesAttackingPlay.Missed) s += "A corner from " +
                this.cornerTaker.getName() + " but no one gets to the ball.";
        else s += "A great corner from " + this.cornerTaker.getName() + ", amazing strike from " +
                    this.areaPlayer.getName() + "! But " + this.defender.getName() + " blocked it.";
        return s;
    }
}