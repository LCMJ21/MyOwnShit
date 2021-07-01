package Moments.Plays;

import Player.Player;
import Team.*;

/**
 * This class represents a Freekick in football (soccer).
 * The comments are mainly written in English.
 * */
public class Freekick extends Play {

    // Instance Variables
    private Player freekickTaker;
    private Player goalkeeper;
    private ConsequencesAttackingPlay consequence;

    // Constructors

    /** The "Standard" constructor creates a (useful) Freekick, given all it's attributes. */
    public Freekick(int minute, int extraTime, boolean home_or_away, Player freekickTaker, Player goalkeeper){
        super(minute,extraTime,home_or_away);
        this.freekickTaker = freekickTaker.clone();
        this.goalkeeper = goalkeeper.clone();
        double dif =  this.freekickTaker.getOneAttribute(Player.FreeKick) - this.goalkeeper.getOverall();
        consequence = randomAttackingPlay( 0.3, 0.4, 0.3, dif);
    }

    /** The "Special" constructor creates a (useful) Freekick, and founds the players from the teams. */
    public Freekick(int minute, int extraTime, boolean home_or_away, Team attackingTeam, Team defensiveTeam) throws MissingPlayer{
        super(minute,extraTime,home_or_away);
        this.freekickTaker = attackingTeam.getFKTaker();
        this.goalkeeper = defensiveTeam.getGoalkeeper();
        double dif =  this.freekickTaker.getOneAttribute(Player.FreeKick) - this.goalkeeper.getOverall();
        consequence = randomAttackingPlay( 0.3, 0.4, 0.3, dif);
    }

    /** The "copy paste" constructor creates a Freekick, given another Freekick. */
    public Freekick(Freekick freekick){
        super(freekick);
        this.freekickTaker = freekick.freekickTaker.clone();
        this.goalkeeper = freekick.goalkeeper.clone();
        this.consequence = freekick.consequence;
    }

    //Getters and Setters

    public Player getFreeKickTaker() { return freekickTaker.clone(); }
    public void setFreeKickTaker(Player freekickTaker) { this.freekickTaker = freekickTaker.clone(); }
    public Player getGoalkeeper() { return goalkeeper.clone(); }
    public void setGoalkeeper(Player goalkeeper) { this.goalkeeper = goalkeeper.clone(); }
    public ConsequencesAttackingPlay getConsequence() { return consequence; }
    public void setConsequence(ConsequencesAttackingPlay consequence) { this.consequence = consequence; }

    // Public Methods

    /** Was there a Goal ? */
    public boolean isGoal(){ return consequence == ConsequencesAttackingPlay.Goal; }

    /** Basic clone method */
    public Freekick clone(){
        return new Freekick(this);
    }

    /** Basic toString method */
    public String toString(){
        String sb = super.baseString();
        if (consequence == ConsequencesAttackingPlay.Goal) sb +=  "GOAL! Amazing freeKick from "
                + this.freekickTaker.getName() + "!";
        else if (consequence == ConsequencesAttackingPlay.Missed) sb +=  "Missed freeKick by "
                + this.freekickTaker.getName() + ".";
        else sb +=   this.goalkeeper.getName() + " saved a great freeKick by " + this.freekickTaker.getName() + ".";
        return sb;
    }
}