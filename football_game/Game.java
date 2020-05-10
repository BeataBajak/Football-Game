package pl.project.football_game;

import java.io.*;
import java.util.*;

public class Game {

    @Override
    public String toString() {
        return "Game{}";
    }

    public static class TeamsComparator implements Comparator<Team>{
        private List<Comparator<Team>> listComparators;

        @SafeVarargs
        public TeamsComparator(Comparator<Team>...comparators){
            this.listComparators = Arrays.asList(comparators);
        }

        @Override
        public int compare(Team A, Team B){
            for(Comparator<Team> comparator : listComparators){
                int result = comparator.compare(A,B);
                if(result!=0){
                    return result;
                }
            }
            return 0;
        }
    }

    public static class PointsComparator implements Comparator<Team>{
        @Override
        public int compare(Team A, Team B){
            if(A.points<B.points){
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static class GoalsScoredComparator implements Comparator<Team>{
        @Override
        public int compare(Team A, Team B){
            if(A.numberOfGoalsScored<B.numberOfGoalsScored){
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static class LpComparator implements Comparator<Team>{
        @Override
        public int compare(Team A, Team B){
            if(A.lp<B.lp){
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static class GoalsLostComparator implements Comparator<Team>{
        @Override
        public int compare(Team A, Team B){
            if(A.numberOfGoalsLost>B.numberOfGoalsLost){
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        String path;
        String fileName;
        Scanner input = new Scanner(System.in);
        System.out.println("Please put the path where the file with teams' names is saved:");
        path = input.nextLine();
        System.out.println("file's name:");
        fileName = input.nextLine();
        //Path filePath = Paths.get(path);
        List<Team> teamsList = new LinkedList<>();

        int temp=0;
        try {
            FileReader fileReader = new FileReader(fileName);
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
            Collections.sort(teamsList,new TeamsComparator(
                    new PointsComparator(),
                    new GoalsScoredComparator())
                    //new GoalsLostComparator())
            );
            System.out.printf("%-25s %25s %10s %25s %25s", "TEAM", "NUMBER OF MATCHES WON", "POINTS", "NUMBER OF GOALS SCORED", "NUMBER OF GOALS LOST");
            System.out.println();

            for (int k = 1; k<teamsList.size();k++){
                System.out.printf("%-25s %25s %10s %25s %25s", teamsList.get(k-1).teamName, teamsList.get(k-1).numberOfMatchesWon, teamsList.get(k-1).points, teamsList.get(k-1).numberOfGoalsScored, teamsList.get(k-1).numberOfGoalsLost);
                System.out.println();
            }
            System.out.println();
            Collections.sort(teamsList,new LpComparator());
        }
        Collections.sort(teamsList,new TeamsComparator(
                new PointsComparator(),
                new GoalsScoredComparator())
               // new GoalsLostComparator())
        );
        System.out.printf("%-25s %25s %10s %25s %25s", "TEAM", "NUMBER OF MATCHES WON", "POINTS", "NUMBER OF GOALS SCORED", "NUMBER OF GOALS LOST");
        System.out.println();

        for (int k = 1; k<teamsList.size();k++){
            System.out.printf("%-25s %25s %10s %25s %25s", teamsList.get(k-1).teamName, teamsList.get(k-1).numberOfMatchesWon, teamsList.get(k-1).points, teamsList.get(k-1).numberOfGoalsScored, teamsList.get(k-1).numberOfGoalsLost);
            System.out.println();
        }
    }
}
