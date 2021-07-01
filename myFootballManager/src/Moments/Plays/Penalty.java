package Moments.Plays;

import Player.Player;
import Team.*;

/**
 * This class represents a Freekick in football (soccer).
 * The comments are mainly written in English.
 * */
public class Penalty extends Play {

    // Instance Variables
    private Player penaltyTaker;
    private Player goalkeeper;
    private ConsequencesAttackingPlay consequence;

    // Constructors

    /** The "Standard" constructor creates a (useful) Penalty, given all it's attributes. */
    public Penalty(int minute, int extraTime, boolean home_or_away, Player penaltyTaker, Player goalkeeper){
        super(minute,extraTime,home_or_away);
        this.penaltyTaker = penaltyTaker.clone();
        this.goalkeeper = goalkeeper.clone();
        double dif =  this.penaltyTaker.getOneAttribute(Player.Penalties) - this.goalkeeper.getOverall();
        consequence = randomAttackingPlay( 0.5, 0.3, 0.2, dif);
    }

    /** The "Special" constructor creates a (useful) Penalty, and founds the players from the teams. */
    public Penalty(int minute, int extraTime, boolean home_or_away, Team attackingTeam, Team defensiveTeam) throws MissingPlayer {
        super(minute,extraTime,home_or_away);
        this.penaltyTaker = attackingTeam.getPTTaker();
        this.goalkeeper = defensiveTeam.getGoalkeeper();
        double dif =  this.penaltyTaker.getOneAttribute(Player.Penalties) - this.goalkeeper.getOverall();
        consequence = randomAttackingPlay( 0.5, 0.3, 0.2, dif);
    }

    /** The "copy paste" constructor creates a Penalty, given another Penalty. */
    public Penalty(Penalty penalty){
        super(penalty);
        this.penaltyTaker = penalty.penaltyTaker.clone();
        this.goalkeeper = penalty.goalkeeper.clone();
        this.consequence = penalty.consequence;
    }

    //Getters and Setters

    public Player getPenaltyTaker() { return penaltyTaker.clone(); }
    public void setPenaltyTaker(Player penaltyTaker) { this.penaltyTaker = penaltyTaker.clone(); }
    public Player getGoalkeeper() { return goalkeeper.clone(); }
    public void setGoalkeeper(Player goalkeeper) { this.goalkeeper = goalkeeper.clone(); }
    public ConsequencesAttackingPlay getConsequence() { return consequence; }
    public void setConsequence(ConsequencesAttackingPlay consequence) { this.consequence = consequence; }

    // Public Methods

    /** Was there a Goal ? */
    public boolean isGoal(){ return consequence == ConsequencesAttackingPlay.Goal; }

    /** Basic clone method */
    public Penalty clone(){
        return new Penalty(this);
    }

    /** Basic toString method */
    public String toString(){
        String sb = super.baseString();
        if (consequence == ConsequencesAttackingPlay.Goal) sb += "GOAL! Amazing penalty from " +
                this.penaltyTaker.getName() + "!";
        else if (consequence == ConsequencesAttackingPlay.Missed) sb += "Missed penalty by "
                + this.penaltyTaker.getName() + ".";
        else sb +=  this.goalkeeper.getName() + " saved a great penalty by " + this.penaltyTaker.getName() + ".";
        return sb;
    }
}
