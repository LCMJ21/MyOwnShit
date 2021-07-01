package Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * This class represents a football (soccer) player.
 * The comments are mainly written in English.
 * */
public abstract class Player implements Comparable<Player>, Serializable {

    /** Player attributes - range (0-100) */
    public static final int Composure = 0;
    public static final int Concentration = 1;
    public static final int Determination = 2;
    public static final int TeamWork = 3;
    public static final int Technique = 4;
    public static final int Positioning = 5;
    public static final int AreaCommand = 6;
    public static final int Throwing = 7;
    public static final int Kicking = 8;
    public static final int Reflexes = 9;       //Elasticidade
    public static final int OneOnOnes = 10;
    public static final int Marking = 11;
    public static final int Tackling = 12;      //recuperacao
    public static final int Strength = 13;
    public static final int Heading = 14;       //cabeca
    public static final int Bravery = 15;       //destreza
    public static final int Crossing = 16;      //cruzamento
    public static final int Pace = 17;          //Velocidade
    public static final int Stamina = 18;       //resistencia
    public static final int Passing = 19;       //passe
    public static final int OffTheBallPlay = 20;
    public static final int Vision = 21;
    public static final int LongShots = 22;
    public static final int Anticipation = 23;  //impulsao
    public static final int Acceleration = 24;
    public static final int Agility = 25;
    public static final int Dribbling = 26;
    public static final int Flair = 27;
    public static final int Finishing = 28;     //Remate
    public static final int FirstTouch = 29;
    public static final int WorkRate = 30;
    public static final int FreeKick = 31;
    public static final int Penalties = 32;
    public static final int Corners = 33;

    public static final int NumAttributes = 34; // Number of total attributes

    /** Value of attributes */
    private static final int NumCommon = 5;  // Number of common attributes
    public static final int NumSpecial = 6;  // Number of position special attributes
    private static final double ValCommon = 0.15/ NumCommon; // Value of common attributes
    public static final double ValSpecial = 0.85/NumSpecial; // Value of special attributes

    // Instance Variables
    private String name;               // Name of the player.
    private int number;                // Number of the player (shirt).
    private final int [] attributes;   // Array whit all attributes of players
    private int overall;               // Overall rating of the player.
    private boolean subbedOff;         // Was the player substituted this game ?
    private List<String> teamHistory;  // List whit the name of the players teams

    // Constructors

    /** The "empty" constructor creates a (useless) player, with no information whatsoever.  */
    public Player() {
        name = "NoPlayerNameGiven";
        number = -1;           // invalid number, of course
        attributes = new int[NumAttributes];
        for (int i = 0;i < NumAttributes;i++) attributes[i] = 0;
        subbedOff = false;
        teamHistory = new ArrayList<>();
    }

    /** The "Sub-Standard" constructor creates a (useful) player,
     * given all it's attributes and its subbed off status. */
    public Player(String name, int number, int [] attributes, boolean subbedOff){
        this.name = name;
        this.number = number;
        this.attributes = new int[NumAttributes];
        System.arraycopy(attributes, 0, this.attributes, 0, NumAttributes);
        this.subbedOff = subbedOff;
        teamHistory = new ArrayList<>();
    }

    /** The "Standard" constructor creates a (useful) player, given all it's attributes. */
    public Player(String name, int number, int [] attributes){
        this.name = name;
        this.number = number;
        this.attributes = new int[NumAttributes];
        System.arraycopy(attributes, 0, this.attributes, 0, NumAttributes);
        this.subbedOff = false;
        teamHistory = new ArrayList<>();
    }

    /** The "copy paste" constructor creates a player, given another player. */
    public Player(Player player){
        this.name = player.name;
        this.number = player.number;
        this.attributes = new int[NumAttributes];
        System.arraycopy(player.attributes, 0, this.attributes, 0, NumAttributes);
        this.overall = player.overall;
        this.subbedOff = player.subbedOff;
        teamHistory = new ArrayList<>(player.teamHistory);
    }

    //Getters and Setters

    public List<String> getTeamHistory() {
        return new ArrayList<>(teamHistory);
    }

    public void setTeamHistory(List<String> teamHistory) {
        this.teamHistory = new ArrayList<>(teamHistory);
    }

    public boolean isSubbedOff() { return subbedOff; }

    public void setSubbedOff(boolean subbedOff) { this.subbedOff = subbedOff; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() { return number; }

    public void setNumber(int number) { this.number = number; }

    public int getOverall() { return overall; }

    public void setOverall(int overall) { this.overall = overall; }

    public int [] getAttributes() {
        int [] copy = new int[NumAttributes];
        System.arraycopy(this.attributes, 0,copy , 0, NumAttributes);
        return copy;
    }

    public void setAttributes(int[] attributes) { System.arraycopy(attributes, 0, this.attributes, 0, NumAttributes); }

    public int getOneAttribute(int positioning){ return attributes[positioning]; }

    // Public Methods

    /** Basic compareTo method that compares 2 PLayer by their position */
    public int compareTo(Player otherPlayer) {
        return Integer.compare(representedBy(), otherPlayer.representedBy());
    }

    /** Basic clone method */
    public abstract Player clone();

    /** Basic equals method - Same name implies same player ???*/
    public boolean equals(Object obj){
        if (this == obj) return true;

        if ((obj == null)) return false;

        Player p = (Player) obj;
        return this.name.equals(p.getName());
    }

    /** Every player uses this baseString */
    public String baseString(){
        StringBuilder sb = new StringBuilder(this.name + " Number:" + this.number + " Overall:" + this.overall
                + "\n TeamHistory");
        teamHistory.forEach(teamName -> sb.append(" -> ").append(teamName));
        return sb.toString();
    }

    /** Adds the teamName to his History if is not there */
    public void addToHistory(String teamName){
        int ind = teamHistory.size() - 1;
        if ( ind == -1 || ! (teamHistory.get(ind).equals(teamName))){
            teamHistory.add(teamName);
        }
    }

    /** Basic toString method */
    public abstract String toString();

    /** The number that represents a position */
    public abstract int representedBy();

    /** If a class of player is attacking or defensive minded */
    public abstract boolean isAttacking();
    public abstract boolean isDefensive();

    /** Method to calculate a players overall given its attributes*/
    public abstract double generateOverall();

    /** Method to calculate a players common attribute overall */
    public double commonOverall(){
        //All the players have these stats count towards overall
        return ((attributes[Composure] + attributes[Concentration] + attributes[Determination]
                + attributes[TeamWork] + attributes[Technique]) * ValCommon);
        }
}