package Player;

/**
 * This class represents a football (soccer) Winger.
 * The comments are mainly written in English.
 * */
public class Winger extends Player{

    // Constructors
    /** The "empty" constructor creates a (useless) Winger, with no information whatsoever.  */
    public Winger() { super(); }

    /** The "Standard" constructor creates a (useful) Winger, given all it's attributes. */
    public Winger(String name, int number, int [] attributes){
        super(name,number,attributes);
        this.setOverall((int) Math.round(this.generateOverall()));
    }

    /** The "Sub-Standard" constructor creates a (useful) Winger,
     * given all it's attributes and its subbed off status. */
    public Winger(String name, int number, int [] attributes, boolean subbedOff){
        super(name,number,attributes, subbedOff);
        this.setOverall((int) Math.round(this.generateOverall()));
    }

    /** The "copy paste" constructor creates a Winger, given another Winger. */
    public Winger(Winger player){ super(player); }

    // Public Methods

    /** Basic clone method */
    public Winger clone(){
        return new Winger(this);
    }

    /** If a class of player is attacking or defensive minded */
    public boolean isAttacking() { return true; }
    public boolean isDefensive() { return false; }

    /** Basic toString method */
    public String toString(){
        return "Winger " + baseString();
    }

    /** The number that represents a position */
    public int representedBy(){ return 5; }

    /** Method to calculate a players overall given its Winger attributes*/
    public double generateOverall(){
        double overall_WG = (getOneAttribute(Pace) + getOneAttribute(Acceleration) + getOneAttribute(Agility) +
                getOneAttribute(Dribbling) + getOneAttribute(Crossing) + getOneAttribute(Flair)) * ValSpecial;
        return commonOverall() + overall_WG;
    }
}
