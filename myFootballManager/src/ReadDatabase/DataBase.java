package ReadDatabase;

import Match.MatchDay;
import Player.*;
import Team.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DataBase implements Serializable {

    // Instance Variables
    private Map<String, Team> teams;

    // Constructors
    /** The "empty" constructor creates a new database whit no info.  */
    public DataBase(){
        teams = new HashMap<>();
    }

    /** The "parametric" constructor creates a database, given another the parameters. */
    public DataBase(Map<String,Team> teams) {
        this.teams = new HashMap<>(teams);
    }

    /** The "copy paste" constructor creates a database, given another database. */
    public DataBase(DataBase dataBase) {
        this.teams = new HashMap<>(dataBase.teams);
    }

    /** Loads a Database on a binary file */
    public  DataBase (String filepath,boolean binary) throws IOException, ClassNotFoundException, LinhaIncorretaException {
        if(binary) {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(filepath));
            DataBase temp = new DataBase((DataBase) is.readObject());
            this.teams = temp.getTeams();
            is.close();
        }
        else {

            teams = new HashMap<>();

            File file = new File(filepath);
            Scanner readFile = new Scanner(file);
            String line;
            Team team = null;
            Player player;
            while(readFile.hasNextLine()) {
                line = readFile.nextLine();
                String [] linhaPartida = line.split(":");

                switch (linhaPartida[0]) {
                    case "Equipa":
                        if(team != null) {
                            team.bestLineup();
                            team.bestTakers();
                        }
                        team = new Team(linhaPartida[1]);
                        teams.put(linhaPartida[1],team);
                        break;
                    case "Jogo":
                        this.addGame(linhaPartida[1]);
                        break;
                    default:
                        player = makePlayer(linhaPartida[0],linhaPartida[1]);
                        if(team == null) throw new LinhaIncorretaException("Linha incorreta Exception");
                        team.addPlayerSquad(player);
                }

            }
            /* For the last team */
            if(team == null) throw new LinhaIncorretaException("Linha incorreta Exception");
                team.bestLineup();
                team.bestTakers();


        }
    }

    // Getters and Setters

    public Map<String, Team> getTeams() { return teams.values().stream()
            .collect(Collectors.toMap(Team :: getName,Team :: clone)); }

    public void setTeams(Map<String, Team> teams) {
        this.teams = teams.values().stream()
                .collect(Collectors.toMap(Team :: getName,Team :: clone));
    }

    public Team getTeam (String teamName) throws MissingTeam{
        if (!teams.containsKey(teamName)) throw new MissingTeam(teamName + " is not on this database");
        return teams.get(teamName).clone();
    }

    // Public Methods

    /** Basic clone method */
    public DataBase clone() { return new DataBase(this);}

    /** Basic toString method */
    public String toString(){
        return teams.values().stream().map(Team::toString).collect(Collectors.joining("\n\n"));
    }

    /** Saves a Database on a binary file */
    public  void saveBinary(String filepath) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filepath));
        os.writeObject(this);
        os.flush();
        os.close();
    }

    /** Adds game from professor files to the respective teams */
    private void addGame(String information) throws LinhaIncorretaException {
        String [] informationSplit = information.split(",");
        Team home = teams.get(informationSplit[0]);
        Team away = teams.get(informationSplit[1]);
        if(home == null || away == null) throw new LinhaIncorretaException("Linha incorreta Exception");
        MatchDay match = new MatchDay(home.clone(),away.clone(),informationSplit[4],Integer.parseInt(informationSplit[2])
                ,Integer.parseInt(informationSplit[3]), Arrays.stream(informationSplit)
                .skip(5)
                .toArray(String[]::new));
        home.addsMatch(match);
        away.addsMatch(match);
    }


    public void addHistory(MatchDay match, String team1_name, String team2_name) {
        Team team1 = teams.get(team1_name);
        Team team2 = teams.get(team2_name);
        team1.addsMatch(match);
        team2.addsMatch(match);
    }

    public void transfer(String out, String in,String p) {
        Team outTeam = this.teams.get(out);
        Team inTeam = this.teams.get(in);
        Player player = outTeam.removePlayer(p);
        outTeam.bestTakers();
        outTeam.bestLineup();
        inTeam.addPlayerSquad(player);
        inTeam.bestLineup();
        inTeam.bestTakers();

    }


    // Private Methods

    /** Analizes all the information from a player and creates one */
    private Player makePlayer(String pos, String information) throws LinhaIncorretaException {
        int [] atrb = new int[Player.NumAttributes];
        Player player = null;
        String [] informationSplit = information.split(",");

        /* randomizes all atributes and adds the given ones */
        Random rand = new Random();
        for(int i = 0; i < Player.NumAttributes; i++) atrb[i] = rand.nextInt(100);
        atrb[17] = Integer.parseInt(informationSplit[2]);
        atrb[18] = Integer.parseInt(informationSplit[3]);
        atrb[15]= Integer.parseInt(informationSplit[4]);
        atrb[23]= Integer.parseInt(informationSplit[5]);
        atrb[14]= Integer.parseInt(informationSplit[6]);
        atrb[28]= Integer.parseInt(informationSplit[7]);
        atrb[19]= Integer.parseInt(informationSplit[8]);


        if(pos.equals("Medio")) atrb[12]= Integer.parseInt(informationSplit[9]);
        else if(pos.equals("Lateral")) atrb[16]= Integer.parseInt(informationSplit[9]);
        else if(pos.equals("Guarda-Redes")) atrb[9]= Integer.parseInt(informationSplit[9]);
        switch (pos) {
            case "Guarda-Redes":
                player = genPlayer(informationSplit[0],Integer.parseInt(informationSplit[1]), atrb, 0);
                break;
            case "Defesa":
                player = genPlayer(informationSplit[0],Integer.parseInt(informationSplit[1]), atrb, 1);
                break;
            case "Lateral":
                player = genPlayer(informationSplit[0],Integer.parseInt(informationSplit[1]), atrb, 4);
                break;
            case "Medio":
                player = genPlayer(informationSplit[0],Integer.parseInt(informationSplit[1]), atrb, 3);
                break;
            case "Avancado":
                player = genPlayer(informationSplit[0],Integer.parseInt(informationSplit[1]), atrb, 5);
                break;

        }
        return player;

    }



    private Player genPlayer(String name, int number, int [] attributes, int pos) throws LinhaIncorretaException{
        return switch (pos) {
            case 0 -> new GoalKeeper(name, number, attributes);
            case 1 -> new Defender(name, number, attributes);
            case 2 -> new FullBack(name, number, attributes);
            case 3 -> new Midfielder(name, number, attributes);
            case 4 -> new Winger(name, number, attributes);
            case 5 -> new Striker(name, number, attributes);
            default -> throw new LinhaIncorretaException("Linha incorreta Exception");
        };
    }


}
