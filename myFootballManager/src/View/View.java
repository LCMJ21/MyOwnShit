package View;

import ReadDatabase.DataBase;
import Team.Team;

/**
 * View
 */
public class View {

    public static void mainMenu() {
        StringBuilder sb = new StringBuilder("---------------Menu--------------");
        sb.append("\n(1) - Match");
        sb.append("\n(2) - Transfers");
        sb.append("\n(3) - LoadDatabase");
        sb.append("\n(4) - SaveDatabase");
        sb.append("\n(5) - View database");
        sb.append("\n(6) - Quit");
        System.out.println(sb);
    }
    /** Prints an object */
    public static void printer(Object o){System.out.println(o);}

    /**Prints questions related to teams */
    public static void askTeam(int i) {
        if(i == 1) System.out.print("Choose your team: ");
        else if(i == 2) System.out.print("Choose adversary team: ");
        else if(i == 3) System.out.print("Choose the team you want to see [name/quit]: ");
        else  if(i == 4) System.out.print("Choose the team with the Player you want to transfer: ");
        else if(i == 5) System.out.print("Choose the new team for the player: ");
        else if(i == 6) System.out.print("Do you want to change your FirstEleven? [y/n]: ");
        else if(i == 7) System.out.print("Do you want to see the match history? [y/n]: ");
        else if(i == 8) System.out.print("Do you want to change any position? [y/n]: ");
        else if(i == 10) System.out.print("Do you want to see a match? [date/n]: ");
    }

    /**Prints questions related to Players */
    public static void askPlayer(int i) {

        if(i == 1) System.out.print("Choose the player to transfer: ");
        else if(i == 2) System.out.print("Choose the player to enter: ");
        else if(i == 3) System.out.print("Choose the player to leave: ");
        else if(i == 4) System.out.print("Choose player to change position: ");
        else if(i == 5) System.out.print("Choose new Position: ");

    }

    /** Prints all teams available in database */
    public static void availableTeams(DataBase data) {
        StringBuilder sb = new StringBuilder("-------------\tAvailable Teams\t-------------\n");
        for(Team t : data.getTeams().values()) {
            sb.append(t.getName() + "  Overall: " + t.getTeamOverall() + "\n");
        }
        System.out.println(sb);
    }

    /** Prints questions related to Database */
    public static void askDatabase(int i) {
    if(i == 1) {
        System.out.println("Do you want to load our custom database or an external/Professor database?");
        System.out.print("[custom/external] ");
    }else if(i == 2) System.out.print("Is the file binary [y/n]: ");
    else System.out.print("Write filepath: ");


    }
    /** Prints exceptions */
    public static void exceptionPrinter(Exception e) {
        System.out.println(e.getMessage());
    }
    public static void noDatabase() {
        System.out.println("No Database loaded please load one.\n");
    }

}