package Player;

/**
 * This class represents a football (soccer) FullBack.
 * The comments are mainly written in English.
 * */
public class FullBack extends Player{

    // Constructors

    /** The "empty" constructor creates a (useless) FullBack, with no information whatsoever.  */
    public FullBack() {
        super();
    }

    /** The "Standard" constructor creates a (useful) FullBack, given all it's attributes. */
    public FullBack(String name, int number, int [] attributes){
        super(name,number,attributes);
        this.setOverall((int) Math.round(this.generateOverall()));
    }

    /** The "Sub-Standard" constructor creates a (useful) FullBack,
     * given all it's attributes and its subbed off status. */
    public FullBack(String name, int number, int [] attributes, boolean subbedOff){
        super(name,number,attributes, subbedOff);
        this.setOverall((int) Math.round(this.generateOverall()));
    }

    /** The "copy paste" constructor creates a FullBack, given another FullBack. */
    public FullBack(FullBack player){
        super(player);
    }

    // Public Methods

    /** Basic clone method */
    public FullBack clone(){
        return new FullBack(this);
    }

    /** Basic toString method */
    public String toString(){
        return "FullBack " + baseString();
    }

    /** The number that represents a position */
    public int representedBy(){ return 3; }

    /** If a class of player is attacking or defensive minded */
    public boolean isAttacking() { return false; }
    public boolean isDefensive() { return true; }

    /** Method to calculate a players overall given its FullBack attributes*/
    public double generateOverall(){
        double overall = (getOneAttribute(Crossing) + getOneAttribute(Pace) + getOneAttribute(Stamina) +
                getOneAttribute(Tackling) + getOneAttribute(Passing) + getOneAttribute(OffTheBallPlay)) * ValSpecial;
        return commonOverall() + overall;
    }
}
