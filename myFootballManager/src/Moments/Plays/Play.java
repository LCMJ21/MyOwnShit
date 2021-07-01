package Moments.Plays;

import Moments.Moment;
import Player.*;
import Team.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This class represents a play of football (soccer).
 * The comments are mainly written in English.
 * */
public abstract class Play extends Moment {

    final Random rand = new Random();

    public static final double Multiplier = 0.01;

    /** What a attacking play generates */
    enum ConsequencesAttackingPlay {
        Goal,
        Missed,
        Saved,
    }

    /** What a fail generates */
    enum Fail {
        CounterAttack,
        Outside,
        LostBall,
    }

    // Constructors

    /** The "Standard" constructor creates a (useful) play, given all it's variables. */
    public Play(int minute, int extraTime, boolean home_or_away) {
        super(minute,extraTime,home_or_away);
    }

    /** The "copy paste" constructor creates a play, given another play. */
    public Play(Play play) { super(play); }

    // Public Methods

    public abstract boolean isGoal();

    /** Generates the consequence of a play given the probabilities */
    public ConsequencesAttackingPlay randomAttackingPlay(double baseGoalProb
            , double baseSavedProb, double baseMissProb, double dif) {
        dif = analiseDif((int) dif) * Multiplier;
        double temp = rand.nextDouble();
        double probability = 0;

        probability += baseGoalProb + dif;
        if (temp < probability) return ConsequencesAttackingPlay.Goal;
        probability += baseSavedProb - (dif / 2);
        if (temp < probability) return ConsequencesAttackingPlay.Missed;
        probability += baseMissProb - (dif / 2);
        if (temp < probability) return ConsequencesAttackingPlay.Saved;
        return null; // Create a exception
    }

    /** Generates the consequence of a fail given the probabilities */
    public Fail randomFail(double baseCounterAttackProb
            , double baseLostBallProb, double baseOutsideProb, double dif) {
        dif = analiseDif((int) dif) * Multiplier;
        double temp = rand.nextDouble();
        double probability = 0;

        probability += baseCounterAttackProb + dif;
        if (temp < probability) return Fail.CounterAttack;
        probability += baseLostBallProb - (dif / 2);
        if (temp < probability) return Fail.LostBall;
        probability += baseOutsideProb - (dif / 2);
        if (temp < probability) return Fail.Outside;
        return null; // Create a exception
    }

    /** Chooses a player given the probabilities */
    public Player chosePlayer(Team team, double pGoalkeeper, double pDefender, double pFullBack
            , double pMidfielder, double pWinger, double pStriker) throws MissingPlayer {
        Class finalC = chosePosition(team,pGoalkeeper,pDefender,pFullBack,pMidfielder,pWinger,pStriker);

        List<Player> list = team.getFirstEleven().values().stream()
                .filter(player -> player.getClass() == finalC).collect(Collectors.toList());
        double avg = list.stream().mapToInt(Player::getOverall).average().orElse(0);
        double probPlayer = 1.0/(list.size());

        double random = rand.nextDouble(), prob = 0;
        for (Player player : list) {
            prob += probPlayer + (analiseDif((int) (player.getOverall() - avg)) * Multiplier);
            if (random < prob) return player;
        }

        throw new MissingPlayer("Not enough players");
    }

    /** Chooses a position given the probabilities */
    private Class chosePosition(Team team, double pGoalkeeper, double pDefender, double pFullBack
            , double pMidfielder, double pWinger, double pStriker) {
        double prob = 0, max = 1;

        if (team.numberPlayersClass(GoalKeeper.class) == 0) { max -= pGoalkeeper; pGoalkeeper = 0; }
        if (team.numberPlayersClass(Defender.class) == 0) { max -= pDefender; pDefender = 0; }
        if (team.numberPlayersClass(FullBack.class) == 0) { max -= pFullBack; pFullBack = 0; }
        if (team.numberPlayersClass(Midfielder.class) == 0) { max -= pMidfielder; pMidfielder = 0; }
        if (team.numberPlayersClass(Winger.class) == 0) { max -= pWinger; pWinger = 0; }
        if (team.numberPlayersClass(Striker.class) == 0) { max -= pStriker; pStriker = 0; }

        double random = rand.nextDouble() * max;
        prob += pGoalkeeper;
        if (random < prob) return GoalKeeper.class;
        prob += pDefender;
        if (random < prob) return Defender.class;
        prob += pFullBack;
        if (random < prob) return FullBack.class;
        prob += pMidfielder;
        if (random < prob) return Midfielder.class;
        prob += pWinger;
        if (random < prob) return Winger.class;
        prob += pStriker;
        if (random < prob) return Striker.class;
        return null; // Create a exception
    }

    /** Calculates the difference between 2 overalls */
    private int analiseDif (int dif){
        if (dif > 40) return 40;
        else if (dif < -40) return -40;
        else return dif;
    }
}
