package Ficha3;

enum STATE {
    KICK_OFF,
    IN_PLACE,
    FINISHED
}

public class Game {

    private String home;
    private String away;
    private int goalsHome;
    private int goalsAway;
    private STATE st;

    public Game(String home,String away){
        this.home = home;
        this.away = away;
        this.goalsAway = this.goalsHome = 0;
        this.st = STATE.KICK_OFF;
    }

    public Game(String home,String away,int g_home, int g_away){
        this.home = home;
        this.away = away;
        this.goalsAway = g_home;
        this.goalsHome = g_away;
        this.st = STATE.IN_PLACE;

    }

    public void startGame(){
        this.st = STATE.IN_PLACE;
    }

    public void endGame(){
        this.st = STATE.FINISHED;
    }

    public void goloVisitado(){
        this.goalsHome++;
    }

    public void goloVisitante(){
        this.goalsAway++;
    }

    public String resultadoActual(){
        return home + " "+ goalsHome + "-" + goalsAway + " " + away;
    }







}
