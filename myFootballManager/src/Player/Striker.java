package Player;

/**
 * This class represents a football (soccer) Striker.
 * The comments are mainly written in English.
 * */
public class Striker extends Player{

    // Constructors

    /** The "empty" constructor creates a (useless) Striker, with no information whatsoever.  */
    public Striker() {
        super();
    }

    /** The "Standard" constructor creates a (useful) Striker, given all it's attributes. */
    public Striker(String name, int number, int [] attributes){
        super(name,number,attributes);
        this.setOverall((int) Math.round(this.generateOverall()));
    }

    /** The "Sub-Standard" constructor creates a (useful) Striker,
     * given all it's attributes and its subbed off status. */
    public Striker(String name, int number, int [] attributes, boolean subbedOff){
        super(name,number,attributes, subbedOff);
        this.setOverall((int) Math.round(this.generateOverall()));
    }

    /** The "copy paste" constructor creates a Striker, given another Striker. */
    public Striker(Striker player){
        super(player);
    }

    // Public Methods

    /** Basic clone method */
    public Striker clone(){
        return new Striker(this);
    }

    /** Basic toString method */
    public String toString(){
        return "Striker " + baseString();
    }

    /** The number that represents a position */
    public int representedBy(){ return 6; }

    /** If a class of player is attacking or defensive minded */
    public boolean isAttacking() { return true; }
    public boolean isDefensive() { return false; }

    /** Method to calculate a players overall given its Striker attributes*/
    public double generateOverall(){
        double overall = (getOneAttribute(Finishing) + getOneAttribute(FirstTouch) + getOneAttribute(WorkRate) +
                getOneAttribute(Dribbling) + getOneAttribute(Pace) + getOneAttribute(Heading)) * ValSpecial;
        return commonOverall() + overall;
    }
}
