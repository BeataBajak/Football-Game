package pl.project.football_game;

import java.io.*;
import java.util.*;

public class Game {

    @Override
    public String toString() {
        return "Game{}";
    }

    List<Team> sortScores(List<Team> list){
        Comparator<Team> comparator = Comparator.comparing(team -> team.points);
        comparator = comparator.thenComparing(Comparator.comparing(team -> team.numberOfGoalsScored));

        // Sort the stream:
        Stream<Team> personStream = list.stream().sorted(comparator);
        List<Team> sortedTeamsList = personStream.collect(Collectors.toList());

        //return the list
        Collections.reverse(sortedTeamsList);
        return sortedTeamsList;
    }

    void printScore (List<Team> teamsList){
        List<Team> sortedTeamsList = sortScores(teamsList);

        System.out.printf("%-25s %25s %10s %25s %25s", "TEAM", "NUMBER OF MATCHES WON", "POINTS", "NUMBER OF GOALS SCORED", "NUMBER OF GOALS LOST");
        System.out.println();

        for (int k = 1; k<teamsList.size();k++){
            System.out.printf("%-25s %25s %10s %25s %25s", sortedTeamsList.get(k-1).teamName, sortedTeamsList.get(k-1).numberOfMatchesWon, sortedTeamsList.get(k-1).points, sortedTeamsList.get(k-1).numberOfGoalsScored, sortedTeamsList.get(k-1).numberOfGoalsLost);
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException{
        pl.project.football_game.Game game = new pl.project.football_game.Game();
        List<Team> teamsList = new LinkedList<>();

        int temp=0;
        try {
            FileReader fileReader = new FileReader("src/pl/football_game/teams.txt");
            BufferedReader bufferReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferReader.readLine()) != null) {
                temp+=1;
                Team team = new Team();
                team.teamName=line;
                teamsList.add(team);
                team.lp+=temp;
            }
            fileReader.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (args.length > 0) {
            try {
                if (teamsList.size() % 2 == 1) {
                    System.out.println("Number of teams need to be even.");
                    System.exit(0);
                }
            } catch (Exception e) {
            }
        }
        int[][][] pairs = new int[teamsList.size() - 1][teamsList.size() / 2][2];
        int w;
        for (int i = 1; i < teamsList.size(); i++) {
            if (i <= teamsList.size() / 2) {
                pairs[2 * i - 2][0][0] = i;
                pairs[2 * i - 2][0][1] = teamsList.size();
                w = 2 * i - 2;
            } else {
                pairs[2 * i - 1 - teamsList.size()][0][1] = i;
                pairs[2 * i - 1 - teamsList.size()][0][0] = teamsList.size();
                w = 2 * i - 1 - teamsList.size();
            }
            int j = i + 1;
            for (int k = 1; k <= teamsList.size() - 2; k++) {
                if (j >= teamsList.size()) {
                    j = 1;
                }
                if (k <= (teamsList.size() - 2) / 2) {
                    pairs[w][k][0] = j;
                } else {
                    pairs[w][teamsList.size() - 1 - k][1] = j;
                }
                j++;
            }
        }
        for (int i = 1; i < teamsList.size(); i++) {
            System.out.println("Game no. "+i);
            for (int j = 0; j < teamsList.size() / 2; j++) {
                Match match = new Match(teamsList.get(pairs[i - 1][j][0]-1), teamsList.get(pairs[i - 1][j][1]-1));

            }
            System.out.println("");

            game.printScore(teamsList);

        }
        game.printScore(teamsList);
    }
}
