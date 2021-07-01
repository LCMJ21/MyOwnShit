package Team;

import Match.MatchDay;
import Player.*;
import ReadDatabase.LinhaIncorretaException;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class represents a football (soccer) team.
 * The comments are mainly written in English.
 * */
public class Team implements Serializable {

    Random rand = new Random();

    // Instance Variables
    private String name;           // Name of the team: "Sporting Clube de Braga"
    private int teamOverall;       // The overall quality of a team (0-100)
    private int atkOverall;        // The attacking quality of a team (0-100)
    private int defOverall;        // The defensive quality of a team (0-100)
    private String freeKickTaker;  // The name of the player that takes the freeKicks
    private String cornerTaker;    // The name of the player that takes the corners
    private String penaltyTaker;   // The name of the player that takes the penalties

    /* We'll use a map to store the players and their respective information.
     * We use 2 groups the reserves and the first eleven
     * We will also use a List to save the MatchHistory of the team */
    private Map<String,Player> Squad;
    private Map<String,Player> FirstEleven;
    private List<MatchDay> MatchHistory;

    // Constructors

    /** The "empty" constructor creates a (useless) team with no information whatsoever. */
    public Team() {
        name = "NoTeamNameGiven";
        teamOverall = atkOverall = defOverall = 0;
        freeKickTaker = cornerTaker = penaltyTaker = "";
        Squad = new HashMap<>();
        FirstEleven = new HashMap<>();
        MatchHistory = new ArrayList<>();
    }

    /** The "Standard" constructor creates a (useful) team, given all it's attributes. */
    public Team(String name){
        this.name = name;
        teamOverall = atkOverall = defOverall = 0;
        freeKickTaker = cornerTaker = penaltyTaker = "";
        Squad = new HashMap<>();
        FirstEleven = new HashMap<>();
        MatchHistory = new ArrayList<>();
    }

    /** The "parametric" constructor creates a Team, given another parameters for a Team. */
    public Team(String name, Map<String,Player> Squad, Map<String,Player> FirstEleven
            , String freeKickTaker, String cornerTaker, String penaltyTaker){
        this.name = name;
        this.penaltyTaker = penaltyTaker;
        this.cornerTaker = cornerTaker;
        this.freeKickTaker = freeKickTaker;
        this.Squad = Squad.values().stream()
                .collect(Collectors.toMap(Player :: getName,Player :: clone));
        this.FirstEleven = FirstEleven.values().stream()
                .collect(Collectors.toMap(Player :: getName,Player :: clone));
        this.generateOverall();
        this.MatchHistory = new ArrayList<>();

        Squad.values().forEach(player -> player.addToHistory(name));
        FirstEleven.values().forEach(player -> player.addToHistory(name));
    }

    /** The "copy paste" constructor creates a Team, given another Team. */
    public Team(Team team){
        this.name = team.name;
        this.Squad = team.Squad.values().stream()
                .collect(Collectors.toMap(Player :: getName,Player :: clone));
        this.FirstEleven = team.FirstEleven.values().stream()
                .collect(Collectors.toMap(Player :: getName,Player :: clone));
        this.teamOverall = team.teamOverall;
        this.atkOverall = team.atkOverall;
        this.defOverall = team.defOverall;
        this.freeKickTaker = team.freeKickTaker;
        this.penaltyTaker = team.penaltyTaker;
        this.cornerTaker = team.cornerTaker;
        this.MatchHistory = new ArrayList<>(team.MatchHistory);

        Squad.values().forEach(player -> player.addToHistory(name));
        FirstEleven.values().forEach(player -> player.addToHistory(name));
    }

    //Getters and Setters/*

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getTeamOverall() { return teamOverall; }
    public void setTeamOverall(int teamOverall) { this.teamOverall = teamOverall; }
    public int getAtkOverall() { return atkOverall; }
    public void setAtkOverall(int atkOverall) { this.atkOverall = atkOverall; }
    public int getDefOverall() { return defOverall; }
    public void setDefOverall(int defOverall) { this.defOverall = defOverall; }
    public Map<String, Player> getSquad() { return new HashMap<String, Player>(this.Squad); }
    public void setSquad(Map<String, Player> squad) { this.Squad = new HashMap<String, Player>(squad); }
    public Map<String, Player> getFirstEleven() { return new HashMap<String, Player>(FirstEleven); }
    public void setFirstEleven(Map<String, Player> firstEleven) { FirstEleven = new HashMap<String, Player>(firstEleven); }
    public Set<MatchDay> getMatchHistory() { return new HashSet<>(MatchHistory); }
    public void setMatchHistory(Set<MatchDay> matchHistory) { MatchHistory = new ArrayList<>(matchHistory); }
    public String getFreeKickTaker() { return freeKickTaker; }
    public void setFreeKickTaker(String freeKickTaker) { this.freeKickTaker = freeKickTaker; }
    public String getCornerTaker() { return cornerTaker; }
    public void setCornerTaker(String cornerTaker) { this.cornerTaker = cornerTaker; }
    public String getPenaltyTaker() { return penaltyTaker; }
    public void setPenaltyTaker(String penaltyTaker) { this.penaltyTaker = penaltyTaker; }


    /** Gets the freeKick taker of the squad*/
    public Player getFKTaker() throws MissingPlayer {
        if (FirstEleven.containsKey(freeKickTaker)) return FirstEleven.get(freeKickTaker).clone();
        else throw new MissingPlayer("FreeKick Taker not in the starting Eleven");
    }

    /** Gets the corner taker of the squad*/
    public Player getPTTaker() throws MissingPlayer{
        if (FirstEleven.containsKey(penaltyTaker)) return FirstEleven.get(penaltyTaker).clone();
        else throw new MissingPlayer("PenaltyTaker Taker not in the starting Eleven");
    }

    /** Gets the penalty taker of the squad*/
    public Player getCRTaker() throws MissingPlayer{
        if (FirstEleven.containsKey(cornerTaker)) return FirstEleven.get(cornerTaker).clone();
        else throw new MissingPlayer("CornerTaker Taker not in the starting Eleven");
    }

    /** Adds a match to the History of the club */
    public void addsMatch(MatchDay matchday){
        MatchHistory.add(matchday.clone());
    }

    // Public Methods

    /** Basic clone method */
    public Team clone(){
        return new Team(this);
    }

    /** Basic equals method - Same name implies same team ???*/
    public boolean equals(Object obj ){
        if (this == obj) return true;

        if ((obj == null) || (obj.getClass() != this.getClass())) return false;

        Team p = (Team) obj;
        return this.name.equals(p.getName());
    }

    /** Basic toString method */
    public String toString(){
        return "Name:" + name +
                " Overall:" + teamOverall +
                " Attacking Overall:" + atkOverall +
                " Defensive Overall:" + defOverall +
                "\nFreeKickTaker:" + freeKickTaker +
                " CornerTaker:" + cornerTaker +
                " PenaltyTaker:" + penaltyTaker +
                "\nSquad: \n" +
                Squad.values().stream().sorted()
                        .map(player -> " " + player.toString()).collect(Collectors.joining("\n")) +
                "\nFirst Eleven: \n" +
                FirstEleven.values().stream().sorted()
                        .map(player -> " " + player.toString()).collect(Collectors.joining("\n"));
    }

    /** Gets a random player from the first eleven */
    public Player offPlayer () throws MissingPlayer {
        int random = Math.abs(rand.nextInt() % 11), i = 0;

        for(Player p : FirstEleven.values()){
            if (i == random) return p;
            i += 1;
        }
        throw new MissingPlayer("Missing a player in the First Eleven\n");
    }

    /** Gets the best player to replace the plater that is subbed off */
    public Player inPlayer (Player off) throws MissingPlayer {
        List<Player> list = new ArrayList<>();
        Squad.values().forEach(player1 -> list.add(changePosition(player1,off.getClass())));
        Optional<Player> p = list.stream().filter( Predicate.not(player -> player.isSubbedOff()))
                .max(Comparator.comparingInt(Player::getOverall));
        if (p.isPresent()) return p.get();
        else throw new MissingPlayer("Missing a player in the Squad\n");
    }

    /** Gets the goalkeeper of the squad*/
    public Player getGoalkeeper() throws MissingPlayer{
        Optional<Player> p = FirstEleven.values().stream()
                .filter(player -> player.getClass().equals(GoalKeeper.class)).findFirst();
        if (p.isPresent()) return p.get();
        else throw new MissingPlayer("No Goalkeeper in this team");
    }

    /** Counts the number of players whit class c */
    public int numberPlayersClass(Class c){
        return (int) FirstEleven.values().stream().filter(player -> player.getClass().equals(c)).count();
    }

    /** Makes a substitution in this team and recalculates the overall */
    public Player foundPlayerByNumber (int number) throws LinhaIncorretaException {
        for (Player player : Squad.values()){
            if (player.getNumber() == number) return player.clone();
        }
        for (Player player : FirstEleven.values()){
            if (player.getNumber() == number) return player.clone();
        }
        throw new  LinhaIncorretaException(" There is no player in the squad whit the number " + number);
    }

    /** Makes a substitution in this team and recalculates the overall */
    public void substitution (Player in ,Player off){
        addPlayerSquad(off);
        movePlayerToFirstEleven(in);
        bestTakers();
        generateOverall();
    }

    /** Adds a player to the squad*/
    public void addPlayerSquad(Player player){

        player.addToHistory(name);
        FirstEleven.remove(player.getName());
        Squad.put(player.getName(),player.clone());
    }

    /** Adds a player to the first eleven */
    public void movePlayerToFirstEleven(Player player){
        player.addToHistory(name);
        if (FirstEleven.size() < 11 && !(player.getClass().equals(GoalKeeper.class) &&
                FirstEleven.values().stream().anyMatch(player1 -> player1.getClass().equals(GoalKeeper.class)))) {
            Squad.remove(player.getName());
            FirstEleven.put(player.getName(),player.clone());
            if (FirstEleven.size() == 11) this.generateOverall();
        }
    }

    /** Adds a player to the first eleven but forces a 442 formation */
    public void movePlayerToFirstEleven442(Player player){
        player.addToHistory(name);
        if (FirstEleven.size() < 11 && !(player.getClass().equals(GoalKeeper.class) &&
                FirstEleven.values().stream().anyMatch(player1 -> player1.getClass().equals(GoalKeeper.class)))
                && !(FirstEleven.values().stream().filter(player1 -> player1.getClass().equals(player.getClass())).count() == 2)
        ) {
            Squad.remove(player.getName(), player);
            FirstEleven.put(player.getName(),player.clone());
            if(FirstEleven.size() == 11) this.generateOverall();
        }
    }

    /** Changes a player position */
    public Player changePosition(Player player, Class c){
        Player p = player;
        if (c.equals(GoalKeeper.class)) p = new GoalKeeper(player.getName(),player.getNumber()
                ,player.getAttributes(),player.isSubbedOff());
        else if (c.equals(Defender.class)) p = new Defender(player.getName(),player.getNumber()
                ,player.getAttributes(),player.isSubbedOff());
        else if (c.equals(FullBack.class)) p = new FullBack(player.getName(),player.getNumber()
                ,player.getAttributes(),player.isSubbedOff());
        else if (c.equals(Midfielder.class)) p = new Midfielder(player.getName(),player.getNumber()
                ,player.getAttributes(),player.isSubbedOff());
        else if (c.equals(Winger.class)) p = new Winger(player.getName(),player.getNumber()
                ,player.getAttributes(),player.isSubbedOff());
        else if (c.equals(Striker.class)) p = new Striker(player.getName(),player.getNumber()
                ,player.getAttributes(),player.isSubbedOff());

        generateOverall();
        return p;
    }

    /** Founds the missing position in a squad on 442 */
    private Class missingPlayer(){
        if (FirstEleven.values().stream().filter(player1 -> player1.getClass().equals(GoalKeeper.class)).count() < 1)
            return GoalKeeper.class;
        else if (FirstEleven.values().stream().filter(player1 -> player1.getClass().equals(Defender.class)).count() < 2)
            return Defender.class;
        else if (FirstEleven.values().stream().filter(player1 -> player1.getClass().equals(FullBack.class)).count() < 2)
            return FullBack.class;
        else if (FirstEleven.values().stream().filter(player1 -> player1.getClass().equals(Midfielder.class)).count() < 2)
            return Midfielder.class;
        else if (FirstEleven.values().stream().filter(player1 -> player1.getClass().equals(Winger.class)).count() < 2)
            return Winger.class;
        else if (FirstEleven.values().stream().filter(player1 -> player1.getClass().equals(Striker.class)).count() < 2)
            return Striker.class;
        else return null;
    }

    /** Tries to put together the best lineup from a team */
    public void bestLineup(){
        FirstEleven.values().forEach(player -> Squad.put(player.getName(),player));
        FirstEleven = new HashMap<>();
        if (Squad.size() >= 11){
            Squad.values().stream().sorted(Comparator.comparingInt(Player::getOverall).reversed())
                    .forEach(this ::movePlayerToFirstEleven442);
            while (FirstEleven.size() < 11){
                Class c = missingPlayer();
                List<Player> list = new ArrayList<>();
                Squad.values().forEach(player1 -> list.add(changePosition(player1,c)));
                list.stream().sorted(Comparator.comparingInt(Player::getOverall).reversed()).forEach(this ::movePlayerToFirstEleven442);
            }
        }
    }

    /** Tries to put the best takers in each category */
    public void bestTakers(){
        Optional<Player> temp;
        temp = FirstEleven.values().stream()
                .max(Comparator.comparingInt(player -> player.getOneAttribute(Player.FreeKick)));
        temp.ifPresent(player -> freeKickTaker = player.getName());
        temp = FirstEleven.values().stream()
                .max(Comparator.comparingInt(player -> player.getOneAttribute(Player.Penalties)));
        temp.ifPresent(player -> penaltyTaker = player.getName());
        temp = FirstEleven.values().stream()
                .max(Comparator.comparingInt(player -> player.getOneAttribute(Player.Corners)));
        temp.ifPresent(player -> cornerTaker = player.getName());
    }

    /** Removes a player from the squad */
    public Player removePlayer(String p) {
        Player player = Squad.remove(p);
        if(player == null) {
            player = FirstEleven.remove(p);
            this.bestLineup();
        }
        return player;
    }

    /** Gets a player from a squad */
    public Player getPlayerSquad(String name) throws MissingPlayer {
        Player player = Squad.get(name);
        if(player == null) {
            throw new MissingPlayer("Player " + name + "not found in Squad" );
        }
        else return player;
    }

    /** Gets a player from a the FirstEleven */
    public Player getPlayerEleven(String name) throws MissingPlayer {
        Player player = FirstEleven.get(name);
        if(player == null) {
            throw new MissingPlayer("Player " + name + "not found in First Eleven" );
        }
        else return player;
    }

    /** Puts the history into a String */
    public String historyToString() {
        StringBuilder sb = new StringBuilder();
        MatchHistory.stream().sorted().forEach(matchDay -> sb.append(matchDay.toString_simple()).append("\n"));
       return sb.toString();
    }

    /** Changes a position of a player */
    public void changeFormation(Player player,Class position) {
        player = this.changePosition(player,position);
        FirstEleven.remove(player.getName());
        FirstEleven.put(player.getName(),player);
    }

    // Private Methods

    /** Generates the overall of a team */
    private void generateOverall(){
        teamOverall = (int) Math.round (FirstEleven.values().stream()
                .mapToInt(Player::getOverall).average().orElse(0));
        atkOverall = (int) Math.round(FirstEleven.values().stream().filter(Player::isAttacking)
                .mapToInt(Player::getOverall).average().orElse(0));
        defOverall = (int) Math.round(FirstEleven.values().stream().filter(Player::isDefensive)
                .mapToInt(Player::getOverall).average().orElse(0));
    }

}