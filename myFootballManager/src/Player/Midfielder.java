package Player;

/**
 * This class represents a football (soccer) Midfielder.
 * The comments are mainly written in English.
 * */
public class Midfielder extends Player{

    // Constructors

    /** The "empty" constructor creates a (useless) Midfielder, with no information whatsoever.  */
    public Midfielder() {
        super();
    }

    /** The "Standard" constructor creates a (useful) Midfielder, given all it's attributes. */
    public Midfielder(String name, int number, int [] attributes){
        super(name,number,attributes);
        this.setOverall((int) Math.round(this.generateOverall()));
    }

    /** The "Sub-Standard" constructor creates a (useful) Midfielder,
     * given all it's attributes and its subbed off status. */
    public Midfielder(String name, int number, int [] attributes, boolean subbedOff){
        super(name,number,attributes, subbedOff);
        this.setOverall((int) Math.round(this.generateOverall()));
    }

    /** The "copy paste" constructor creates a Midfielder, given another Midfielder. */
    public Midfielder(Midfielder player){
        super(player);
    }

    // Public Methods

    /** Basic clone method */
    public Midfielder clone(){
        return new Midfielder(this);
    }

    /** Basic toString method */
    public String toString(){
        return "Midfielder " + baseString();
    }

    /** The number that represents a position */
    public int representedBy(){ return 4; }

    /** If a class of player is attacking or defensive minded */
    public boolean isAttacking() { return true; }
    public boolean isDefensive() { return true; }

    /** Method to calculate a players overall given its Midfielder attributes*/
    public double generateOverall(){
        double overall_GK = (getOneAttribute(Passing) + getOneAttribute(OffTheBallPlay) + getOneAttribute(Vision) +
                getOneAttribute(Stamina) + getOneAttribute(LongShots) + getOneAttribute(Anticipation)) * ValSpecial;
        return commonOverall() + overall_GK;
    }
}
