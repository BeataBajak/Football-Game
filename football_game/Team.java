package pl.project.football_game;

import java.util.Objects;

public class Team {
    String teamName;
    int lp;
    int numberOfMatchesWon;
    int numberOfGoalsScored;
    int numberOfGoalsLost;
    int points;
    int play;

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getNumberOfMatchesWon() {
        return numberOfMatchesWon;
    }

    public void setNumberOfMatchesWon(int numberOfMatchesWon) {
        this.numberOfMatchesWon = numberOfMatchesWon;
    }

    public int getNumberOfGoalsScored() {
        return numberOfGoalsScored;
    }

    public void setNumberOfGoalsScored(int numberOfGoalsScored) {
        this.numberOfGoalsScored = numberOfGoalsScored;
    }

    public int getNumberOfGoalsLost() {
        return numberOfGoalsLost;
    }

    public void setNumberOfGoalsLost(int numberOfGoalsLost) {
        this.numberOfGoalsLost = numberOfGoalsLost;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPlay() {
        return play;
    }

    public void setPlay(int play) {
        this.play = play;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return numberOfMatchesWon == team.numberOfMatchesWon &&
                numberOfGoalsScored == team.numberOfGoalsScored &&
                numberOfGoalsLost == team.numberOfGoalsLost &&
                points == team.points &&
                play == team.play &&
                Objects.equals(teamName, team.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, numberOfMatchesWon, numberOfGoalsScored, numberOfGoalsLost, points, play);
    }

    public Team(){
       /*Object[][] Team = new String[2][11];
        for(int i =0; i<11;i++){
            Player player_i = new Player();
            player_i.number=i;
            Team[0][i]=player_i.number;
            Team[1][i]=player_i.surname;
        }*/
    }
}
