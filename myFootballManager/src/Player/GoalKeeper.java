package Player;

/**
 * This class represents a football (soccer) Goalkeeper.
 * The comments are mainly written in English.
 * */
public class GoalKeeper extends Player{

    // Constructors

    /** The "empty" constructor creates a (useless) GoalKeeper, with no information whatsoever.  */
    public GoalKeeper() {
        super();
    }

    /** The "Standard" constructor creates a (useful) GoalKeeper, given all it's attributes. */
    public GoalKeeper(String name, int number, int [] attributes){
        super(name,number,attributes);
        this.setOverall((int) Math.round(this.generateOverall()));
    }

    /** The "Sub-Standard" constructor creates a (useful) GoalKeeper,
     * given all it's attributes and its subbed off status. */
    public GoalKeeper(String name, int number, int [] attributes, boolean subbedOff){
        super(name,number,attributes,subbedOff);
        this.setOverall((int) Math.round(this.generateOverall()));
    }

    /** The "copy paste" constructor creates a GoalKeeper, given another GoalKeeper. */
    public GoalKeeper(GoalKeeper player){
        super(player);
    }

    // Public Methods

    /** Basic clone method */
    public GoalKeeper clone(){
        return new GoalKeeper(this);
    }

    /** Basic toString method */
    public String toString(){
        return "Goalkeeper " + baseString();
    }

    /** The number that represents a position */
    public int representedBy(){ return 1; }

    /** If a GoalKeeper is attacking or defensive minded */
    public boolean isAttacking() { return false; }
    public boolean isDefensive() { return true; }

    /** Method to calculate a GoalKeeper overall given its GoalKeeper attributes*/
    public double generateOverall(){
        double overall_GK = (getOneAttribute(Positioning) + getOneAttribute(AreaCommand) + getOneAttribute(Throwing) +
                getOneAttribute(Kicking) + getOneAttribute(Reflexes) + getOneAttribute(OneOnOnes)) * ValSpecial;
        return commonOverall() + overall_GK;
    }
}
