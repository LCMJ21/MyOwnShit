import Controller.Controller;

import java.io.FileNotFoundException;

/** Some basic test*/
public class mainLCMJ {
    public static void main(String[] args) throws FileNotFoundException {


        //Controller.run();
        /*
        MatchDay m = new MatchDay(tempDataBase.getTeam("Benfica"), tempDataBase.getTeam("Porto"),"11-04-1999");
        //MatchDay m = new MatchDay(new Team(),new Team(),"");
        try {
            m.simulateGame();
        }
        catch (MissingPlayer mp){
            System.out.println(mp.getMessage());
        }*/

/*



        /*DataBase db = new DataBase(tempDataBase.getTeams());
        try {
            db.saveBinary("database/MainDatabase");
        } catch (IOException e) {
            e.printStackTrace();
        } */





/*
        DataBase db = null;

        try {
            db = new DataBase("database/MainDatabase", true);
        } catch (IOException | ClassNotFoundException | LinhaIncorretaException e) {
            e.printStackTrace();
        }
        */

        /*
        Team home = null,away = null;

        try {
            assert db != null;
            home = db.getTeam("Benfica");
            away = db.getTeam("Porto");
        }
        catch (MissingTeam e){
            System.out.println(e.getMessage());
        }


        MatchDay m = new MatchDay(home, away,"11-04-1999");
        //MatchDay m = new MatchDay(new Team(),new Team(),"");
        try {
            m.simulateGame();
        }
        catch (MissingPlayer mp){
            System.out.println(mp.getMessage());
        }

        //System.out.println(tempDataBase);

        System.out.println(db);
    }*/
    }
}
