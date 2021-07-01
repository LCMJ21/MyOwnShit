package Moments;

import java.io.Serializable;

/**
 * This class represents a moment in football (soccer).
 * The comments are mainly written in English.
 * */
public abstract class Moment implements Serializable {

    // Instance Variables
    private int minute;            //The minute of the play
    private int extraTime;         //The minute of extra time if it exists
    private boolean home_or_away;  //True -> Home team play && False -> Away team play

    // Constructors

    /** The "Standard" constructor creates a (useful) moment, given all it's variables. */
    public Moment(int minute, int extraTime, boolean home_or_away) {
        this.minute = minute;
        this.extraTime = extraTime;
        this.home_or_away = home_or_away;
    }

    /** The "copy paste" constructor creates a moment, given another moment. */
    public Moment(Moment moment) {
        this.minute = moment.minute;
        this.extraTime = moment.extraTime;
        this.home_or_away = moment.home_or_away;
    }

    //Getters and Setters

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getExtraTime() {
        return extraTime;
    }

    public void setExtraTime(int extraTime) {
        this.extraTime = extraTime;
    }

    public boolean isHome_or_away() {
        return home_or_away;
    }

    public void setHome_or_away(boolean home_or_away) {
        this.home_or_away = home_or_away;
    }

    // Public Methods

    /** Basic clone method */
    public abstract Moment clone();

    /** Basic equals method */
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if ((obj == null) || (this.getClass() != obj.getClass())) return false;

        Moment p = (Moment) obj;
        return (this.minute == p.minute) && (this.extraTime == p.extraTime) && (this.home_or_away == p.home_or_away);
    }

    /** Basic toString method */
    public abstract String toString();

    /** Every Moment uses this baseString */
    public String baseString() {
        if (extraTime != 0) return minute + "+" + extraTime + " -> ";
        else return minute + " -> ";
    }
}
