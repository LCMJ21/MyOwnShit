package Match;

import Moments.Moment;
import Moments.Plays.*;
import Moments.Substitution;
import Player.Player;
import ReadDatabase.LinhaIncorretaException;
import Team.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MatchDay implements Serializable, Comparable<MatchDay> {

    public static final double Multiplier = 0.001;
    public static final double SubsMultiplier = 0.05;
    public static final double ProbSubsMultiplier = 0.001;
    Random rand = new Random();

    // Instance Variables
    private final String date;          // Date of the game.
    private final Team teamHome;        // home team.
    private final Team teamAway;        // away team.
    private int goalsHome;              // Number of goals of the home team.
    private int goalsAway;              // Number of goals of the away team.
    private int minute;                 // Number of minutes played in this game
    private int extraTime;              // The minute of extra time if it exists
    private final List<Moment> game;    // Every play in a given game
    private double pHome;               // Probability of the Home team having the possession
    private double pAway;               // Probability of the Away team having the possession
    private double subsLeftHome;        // Substitution left for the Home team
    private double subsLeftAway;        // Substitution left for the Away team

    //getters
    public String getHomeTeam_name (){return teamHome.getName();}
    public String getAwayTeam_name (){return teamAway.getName();}
    public String getdate(){return date;}

    // Constructors
    /** The "random" constructor creates a new matchday, with random squads.  */
    public MatchDay(){
        date = "";
        teamHome = new Team();
        teamAway = new Team();
        minute = goalsAway = goalsHome = extraTime = 0;
        game = new ArrayList<>();
        probPossession(teamHome.getTeamOverall(),teamAway.getTeamOverall());
        subsLeftHome = 3;
        subsLeftAway = 3;
    }

    /** The "main" Constructor for now. */
    public MatchDay(Team home, Team away, String date) {
        this.date = date;
        teamHome = home.clone();
        teamAway = away.clone();
        minute = goalsAway = goalsHome = extraTime = 0;
        game = new ArrayList<>();
        probPossession(teamHome.getTeamOverall(),teamAway.getTeamOverall());
        subsLeftHome = 3;
        subsLeftAway = 3;

    }

    /** The "copy paste" constructor creates a Matchday, given another Matchday. */
    public MatchDay(MatchDay matchday) {
        this.date = matchday.date;
        this.teamHome = matchday.teamHome.clone();
        this.teamAway = matchday.teamAway.clone();
        this.goalsAway = matchday.goalsAway;
        this.goalsHome = matchday.goalsHome;
        this.minute = matchday.minute;
        this.extraTime = matchday.extraTime;
        this.game = matchday.game.stream().map(Moment :: clone).collect(Collectors.toList());
        this.pHome = matchday.pHome;
        this.pAway = matchday.pAway;
        this.subsLeftHome = matchday.subsLeftAway;
        this.subsLeftAway = matchday.subsLeftHome;
    }

    /** Constructor to use in files with games saved */
    public MatchDay(Team home,Team away,String date,int goalsHome,int goalsAway, String [] gameOnString) throws LinhaIncorretaException {
        this.date = date;
        this.teamHome = home;
        this.teamAway = away;
        this.goalsHome = goalsHome;
        this.goalsAway = goalsAway;
        this.game = new ArrayList<>();
        String[] sub;

        int i = 0, count;
        for (count = 0; count < 11; i++, count++) {
            teamHome.movePlayerToFirstEleven(teamHome.foundPlayerByNumber(Integer.parseInt(gameOnString[i])));
        }
        for (count = 0; count < 3; i++, count++) {
            sub = gameOnString[i].split("->");
            Player off = teamHome.foundPlayerByNumber(Integer.parseInt(sub[0]));
            Player in = teamHome.foundPlayerByNumber(Integer.parseInt(sub[1]));
            game.add(new Substitution(in, off, 0, 0, true));
            teamHome.substitution(in, off);
        }
        for (count = 0; count < 11; i++, count++) {
            teamAway.movePlayerToFirstEleven(teamAway.foundPlayerByNumber(Integer.parseInt(gameOnString[i])));
        }
        for (count = 0; count < 3; i++, count++) {
            sub = gameOnString[i].split("->");
            Player off = teamAway.foundPlayerByNumber(Integer.parseInt(sub[0]));
            Player in = teamAway.foundPlayerByNumber(Integer.parseInt(sub[1]));
            game.add(new Substitution(in, off, 0, 0, false));
            teamAway.substitution(in, off);
        }
    }

    // Public Methods

    /** Basic clone method */
    public MatchDay clone() {
        return new MatchDay(this);
    }

    /** Basic equals method */
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if ((obj == null) || (this.getClass() != obj.getClass())) return false;

        MatchDay p = (MatchDay) obj;
        return !p.date.equals(this.date) || !p.teamHome.equals(this.teamHome) || !p.teamAway.equals(this.teamAway);
    }

    /** The "simulateGame" method simulates the matchday from start to end.
     *  It simulates each half and sees if there are extra minutes to be played.
     * */
    public void simulateGame() throws MissingPlayer{
        // Simulates the first half
        while (minute <=45) advanceMinute(false);

        // Simulates the first time extra time
        extraTime += 1;minute -= 1;
        int fhExtraTime = (int) Math.round(game.size()/10.0);
        while (extraTime <= fhExtraTime) advanceMinute(true);
        extraTime = 0;

        simulateSubs(minute);

        // Simulates the second half
        minute++;
        while (minute <=90) {
            advanceMinute(false);
            simulateSubs(minute);
        }

        // Simulates the second time extra time
        extraTime += 1;minute -= 1;
        int shExtraTime = (int) Math.round((game.size()/10.0)) - fhExtraTime;
        while (extraTime <= shExtraTime) {
            advanceMinute(true);
            simulateSubs(minute + extraTime);
        }
    }

    // Private Methods

    /** The "probPossession" using difference from the qualities of the teams
     */
    private void simulateSubs(int time) throws MissingPlayer {
        double prob = ProbSubsMultiplier * time;

        if (rand.nextDouble() < prob){
            prob = 0.50 - ((goalsHome - goalsAway) * SubsMultiplier);
            if (rand.nextDouble() < prob) {
                if (subsLeftHome > 0) {
                    Player off = teamHome.offPlayer();
                    Player in = teamHome.inPlayer(off);
                    off.setSubbedOff(true);
                    game.add(new Substitution(in, off, minute, extraTime, true));
                    subsLeftHome --;
                    teamHome.substitution(in,off);
                    probPossession(teamHome.getTeamOverall(),teamAway.getTeamOverall());
                }
            }
            else{
                if (subsLeftAway > 0) {
                Player off = teamAway.offPlayer();
                Player in = teamAway.inPlayer(off);
                off.setSubbedOff(true);
                game.add(new Substitution(in,off,minute,extraTime,false));
                subsLeftAway --;
                teamAway.substitution(in,off);
                probPossession(teamHome.getTeamOverall(),teamAway.getTeamOverall());
                }
            }
        }
    }

    /** The "probPossession" using difference from the qualities of the teams
     */
    private void probPossession(int t1, int t2){
        double baseProb = 0.06;
        pHome = baseProb + (calcDif(t1,t2) * 0.001);
        pAway = 2 * baseProb;
    }

    /** The "isPlay" using the probabilities of each team making a play
     * decides if there is a play and who makes the play.
     * */
    private int isPlay(){
        double random = rand.nextDouble();

        if (random <= pHome) return 1;
        else if (random <= pAway) return 2;
        return 0;
    }

    /** The "addMinute" method adds a minute to a given game in normal or extra time * */
    private void addMinute(boolean eTime){
        if (eTime) extraTime++;
        else minute++;
    }

    /** The "genRandPlay" using the probabilities of each team generates a play
     * */
    private Play genRandPlay(boolean homeOrAway) throws MissingPlayer{
        double random1 = rand.nextDouble(), random2 = rand.nextDouble();
        Team att,dff;

        if (homeOrAway) {att = teamHome;dff = teamAway;}
        else {att = teamAway;dff = teamHome;}

        double attProb = 0.5 + (calcDif(att.getAtkOverall(),dff.getDefOverall()) * Multiplier);
        if ( random1 <= attProb){
            if ( random2 <= 0.55 ) return new Shot(minute,extraTime,homeOrAway,att.clone(),dff.clone());
            else if (random2 <= 0.6 ) return new Penalty(minute,extraTime,homeOrAway,att.clone(),dff.clone());
            else return new TeamPlay(minute,extraTime,homeOrAway,att.clone(),dff.clone());
        }
        else {
            if (random2 <= 0.6) return new FailedPlay(minute,extraTime,homeOrAway,att.clone(),dff.clone());
            else if (random2 <= 0.8) return new Corner(minute,extraTime,homeOrAway,att.clone(),dff.clone());
            else return new Freekick(minute,extraTime,homeOrAway,att.clone(),dff.clone());
        }
    }

    /** The "addGoal" method adds a goal ,if the plays requires it,
     *  to the team that scores the goal  * */
    private void addGoal(Play plays, boolean team){
        if (plays.isGoal() && team) goalsHome++;
        else if (plays.isGoal()) goalsAway++;
    }

    /** The "advanceMinute" method simulates a minute in a game.
     *  If there is a play that occurred it adds on the list of plays.
     *  If the play gives a special play it simulates it also.
     *  It also adds the goals if goals are scored.
     * */
    private void advanceMinute(boolean eTime) throws MissingPlayer {
        int team = isPlay();
        boolean homeOrAway;

        if (team > 0){
            // Creates a new play for the given team
            homeOrAway = team == 1;
            Play newPlay = genRandPlay(homeOrAway);
            game.add(newPlay); // Adds play to the list of plays of the game
            addGoal(newPlay, homeOrAway); // If there was a goal, adds the goals
        }
        addMinute(eTime); // Adds a minute to the game
    }

    /** The "toString" method generates the String that represents the game.
     *  It prints the final score and every play in the game.
     * */
    public String toString( ){
        StringBuilder text = new StringBuilder("***------------------------------------------------------------***\n" );
        text.append("Date: ").append(date).append(" ").append(teamHome.getName()).append(" VS ")
                .append(teamAway.getName()).append("\n\n");
        for (Moment pl: game) text.append(pl.toString()).append("\n");
        text.append("\nThe final score is ").append(teamHome.getName()).append(" ").append(goalsHome)
                .append(" - ").append(goalsAway).append(" ").append(teamAway.getName())
                .append("\n***------------------------------------------------------------***\n");
        return text.toString();
    }

    /** The "toString_simple" method generates the String that summarizes the game*/
    public String toString_simple() {
        return date + " " + teamHome.getName() + " " + goalsHome + " - " + goalsAway + " " + teamAway.getName();
    }

    // Private Methods

    /** Calculates the difference between 2 overalls */
    private int calcDif (int ovl1 , int ovl2){
        int dif = ovl1 - ovl2;
        if (dif > 40) return 40;
        else if (dif < -40) return -40;
        else return dif;
    }

    /** Basic compareTo method that compares 2 maydays by the date hey were played in */
    public int compareTo(MatchDay matchDay) {
        return this.date.compareTo(matchDay.date);
    }
}
