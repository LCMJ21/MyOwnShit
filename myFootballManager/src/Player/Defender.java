package Player;

/**
 * This class represents a football (soccer) Defender.
 * The comments are mainly written in English.
 * */
public class Defender extends Player{

    // Constructors

    /** The "empty" constructor creates a (useless) Defender, with no information whatsoever.  */
    public Defender() {
        super();
    }

    /** The "Standard" constructor creates a (useful) Defender, given all it's attributes. */
    public Defender(String name, int number, int [] attributes){
        super(name,number,attributes);
        this.setOverall((int) Math.round(this.generateOverall()));
    }

    /** The "Sub-Standard" constructor creates a (useful) Defender,
     * given all it's attributes and its subbed off status. */
    public Defender(String name, int number, int [] attributes, boolean subbedOff){
        super(name,number,attributes, subbedOff);
        this.setOverall((int) Math.round(this.generateOverall()));
    }

    /** The "copy paste" constructor creates a Defender, given another Defender. */
    public Defender(Defender player){
        super(player);
    }

    // Public Methods

    /** Basic clone method */
    public Defender clone(){
        return new Defender(this);
    }

    /** Basic toString method */
    public String toString(){
        return "Defender " + baseString();
    }

    /** The number that represents a position */
    public int representedBy(){ return 2; }

    /** If a Defender is attacking or defensive minded */
    public boolean isAttacking() { return false; }
    public boolean isDefensive() { return true; }

    /** Method to calculate a players overall given its Defender attributes*/
    public double generateOverall(){
        double overall_DF = (getOneAttribute(Marking) + getOneAttribute(Tackling) + getOneAttribute(Positioning) +
                getOneAttribute(Strength) + getOneAttribute(Heading) + getOneAttribute(Bravery)) * ValSpecial;
        return commonOverall() + overall_DF;
    }
}
