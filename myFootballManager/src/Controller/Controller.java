package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import Match.*;
import Player.*;
import ReadDatabase.DataBase;
import ReadDatabase.LinhaIncorretaException;
import ReadDatabase.MissingTeam;
import Team.*;
import View.*;

/**
 * Controller.Controller
 */
public class Controller {

    public static DataBase loadDatabase() {
        DataBase dataBase = null;
        Scanner User_input = new Scanner(System.in);
        /*database load code */
        View.askDatabase(1);
        String dataAnswer = User_input.nextLine();
        if(dataAnswer.equals("custom")) {

            try {
                dataBase = new DataBase("database/CustomDatabase",true);
            } catch (IOException | ClassNotFoundException | LinhaIncorretaException e ) {
                View.exceptionPrinter(e);


            }
        }
        else if(dataAnswer.equals("external")) {
            View.askDatabase(2);
            dataAnswer = User_input.nextLine();
            boolean binary = dataAnswer.equals("y");
            View.askDatabase(3);
            String filepath = User_input.nextLine();

            try {
                dataBase = new DataBase("database/" + filepath,binary);
            } catch (IOException | ClassNotFoundException | LinhaIncorretaException e) {
                View.exceptionPrinter(e);
            }
        }
        return dataBase;
    }

    public static void run() {
        Scanner User_input = new Scanner(System.in);


       DataBase dataBase = Controller.loadDatabase();

        while(true) {
            View.mainMenu();
            int option = User_input.nextInt();
            switch(option) {
                case 1:
                    if(dataBase != null) {
                        MatchDay match = TeamChoosingMenu(dataBase);
                        try {
                        match.simulateGame();
                        View.printer(match);
                        dataBase.addHistory(match,match.getHomeTeam_name(),match.getAwayTeam_name());

                        } catch (MissingPlayer mp) {
                            View.exceptionPrinter(mp);
                        }

                    }
                    else View.noDatabase();
                    break;
                case 2:
                    Controller.TransferMenu(dataBase);
                    break;
                case 3:
                    dataBase = Controller.loadDatabase();
                    break;
                case 4:
                    User_input.nextLine();
                    View.askDatabase(3);
                    String filepath = User_input.nextLine();
                    try {
                        if(dataBase != null) dataBase.saveBinary("database/" + filepath);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    if (dataBase != null) Controller.TeamMenu(dataBase);
                    else View.noDatabase();
                    break;
                case 6:
                    User_input.close();
                    System.exit(0);
                    break;
            }
        }
    }


    //subMenus



    /** Auxiliar controller for choosing teams */
    public static MatchDay TeamChoosingMenu(DataBase dataBase) {
        LocalDate d = LocalDate.now();
        Scanner input = new Scanner(System.in);

        /* Asks for first team */
        View.availableTeams(dataBase);

        View.askTeam(1);
        String myTeam = input.nextLine();
        Team home = null;
        while (home == null) {
            try {
                home = dataBase.getTeam(myTeam);
            } catch (MissingTeam e) {
                View.exceptionPrinter(e);
                View.askTeam(1);
                myTeam = input.nextLine();

            }
        }


        /* Asks and changes FirstEleven */
        View.askTeam(6);
        String answer = input.nextLine();
        if(answer.equals("y")) {
            String in;
            String out;
            Player inPlayer;
            Player outPlayer;
            View.printer(home);
            while(true) {
                View.askPlayer(2);
                in = input.nextLine();
                if(in.equals("quit")) break;
                try {
                    inPlayer = home.getPlayerSquad(in);
                }catch (MissingPlayer e) {
                    View.exceptionPrinter(e);
                    continue;
                }
                View.askPlayer(3);
                out = input.nextLine();
                if(out.equals("quit")) break;
                try {
                    outPlayer = home.getPlayerEleven(out);
                }catch (MissingPlayer e) {
                    View.exceptionPrinter(e);
                    continue;
                }
                home.substitution(inPlayer,outPlayer);
                View.printer(home);
            }
        }
        /* Changes position of any player in first eleven */
        View.askTeam(8);
        answer = input.nextLine();
        if(answer.equals("y")) {
                Controller.positionMenu(home);
        }



        /*Asks for the second team */
        View.askTeam(2);
        String awayTeam = input.nextLine();
        Team away = null;
        while(away == null) {
            try {
                away = dataBase.getTeam(awayTeam);
            } catch (MissingTeam e) {
                View.exceptionPrinter(e);
                View.askTeam(2);
                awayTeam = input.nextLine();
            }
        }
        

        return new MatchDay(home,away,d.toString());
    }


    /** controller for Team Choosing to use in the match method */
    public static void TeamMenu(DataBase database){
        Scanner input = new Scanner(System.in);
        String team_name = "";
        View.availableTeams(database);


        while(!team_name.equals("quit")) {
            View.askTeam(3);
            team_name = input.nextLine();
            Team team = database.getTeams().get(team_name);
            if(team != null) {
                View.printer(team);
                View.askTeam(7);
                String response = input.nextLine();
                if(response.equals("y")) {
                    View.printer(team.historyToString());
                    while (true) {
                        View.askTeam(10);
                        String date = input.nextLine();
                        if(date.equals("n")) break;
                        for(MatchDay m : team.getMatchHistory()) {
                            if(date.equals(m.getdate())) {
                                View.printer(m);
                                break;
                            }
                        }
                    }
                }
            }



        }

    }


    /** controller for transfer method */
    public static void TransferMenu(DataBase dataBase) {
        Scanner input = new Scanner(System.in);
        View.availableTeams(dataBase);
        Team outTeam = null;
        Team inTeam = null;
        Player player = null;
        View.askTeam(4);
        String outTeam_name = input.nextLine();

        while (outTeam == null) {
            try {
                outTeam = dataBase.getTeam(outTeam_name);
            }catch (MissingTeam mt) {
                View.exceptionPrinter(mt);
                View.askTeam(4);
                outTeam_name = input.nextLine();
            }
        }


        View.printer(outTeam);
        View.askPlayer(1);
        String player_name = input.nextLine();
        while (player == null) {
            player = outTeam.getSquad().get(player_name);
            if(player == null) player = outTeam.getFirstEleven().get(player_name);

        }

        View.availableTeams(dataBase);
        View.askTeam(5);
        String inTeam_name = input.nextLine();

        while (inTeam == null) {
            try {
                inTeam = dataBase.getTeam(inTeam_name);
            }catch (MissingTeam mt) {
                View.exceptionPrinter(mt);
                View.askTeam(4);
                inTeam_name = input.nextLine();
            }
        }


        dataBase.transfer(outTeam_name,inTeam_name,player_name);

    }

    /** controller to change a player position */
    public static void positionMenu(Team team) {
        Scanner input = new Scanner(System.in);
        View.printer(team);
        label:
        while (true) {
            String player_name;
            String position_name;
            Class position;
            Player player;
            View.askPlayer(4);
            player_name = input.nextLine();
            if (player_name.equals("quit")) break;
            try {
                player = team.getPlayerEleven(player_name);
            }catch (MissingPlayer mp) {
                View.exceptionPrinter(mp);
                continue;
            }

            View.askPlayer(5);
            position = player.getClass();
            position_name = input.nextLine();
            switch (position_name) {
                case "quit":
                    break label;
                case "Winger":
                    position = Winger.class;
                    break;
                case "Defender":
                    position = Defender.class;
                    break;
                case "FullBack":
                    position = FullBack.class;
                    break;
                case "GoalKeeper":
                    position = GoalKeeper.class;
                    break;
                case "Midfielder":
                    position = Midfielder.class;
                    break;
                case "Striker":
                    position = Striker.class;
                    break;
            }

            team.changeFormation(player,position);
            View.printer(team);
        }
    }
}
