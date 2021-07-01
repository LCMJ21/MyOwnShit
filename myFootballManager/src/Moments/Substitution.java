package Moments;

import Player.Player;

/**
 * This class represents a substitution in football (soccer).
 * The comments are mainly written in English.
 * */
public class Substitution extends Moment {

    // Instance Variables
    private final Player out; // Player that leaves the pitch
    private final Player in;  // Player that enters the pitch

    // Constructors

    /** The "Standard" constructor creates a (useful) substitution, given all it's variables. */
    public Substitution(Player in, Player out, int minute, int extraTime, boolean home_or_away) {
        super(minute,extraTime,home_or_away);
        this.out = out.clone();
        this.in = in.clone();
    }

    /** The "copy paste" constructor creates a substitution, given another substitution. */
    public Substitution(Substitution substitution) {
        super(substitution);
        this.out = substitution.out.clone();
        this.in = substitution.in.clone();
    }

    //Getters and Setters

    public Player getOut() { return out.clone(); }

    public Player getIn() { return in.clone(); }

    // Public Methods

    /** Basic clone method */
    public Substitution clone(){ return new Substitution(this); }

    /** Basic equals method */
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if ((obj == null) || (this.getClass() != obj.getClass())) return false;

        Substitution p = (Substitution) obj;
        return this.in.equals(p.in) && this.out.equals(p.out) && super.equals(p);
    }

    /** Basic toString method */
    public String toString( ){
        String s = super.baseString();
        s += "Substitution " +  out.getName() + " subbed off for " + in.getName() + ".";
        return s;
    }

}
