package pl.project.football_game;

import java.util.Random;

public class Match {

    Random generator = new Random();
    Team A = new Team();
    Team B = new Team();

    public Random getGenerator() {
        return generator;
    }

    public Match (Team A,Team B){
        A.play=1;
        B.play=1;
        int[] result={generator.nextInt(10),generator.nextInt(10)};
        A.numberOfGoalsScored+=result[0];
        A.numberOfGoalsLost+=result[1];
        B.numberOfGoalsScored+=result[1];
        B.numberOfGoalsLost+=result[0];
        if(result[0]>result[1]){
            A.numberOfMatchesWon+=1;
            A.points+=3;
            System.out.println(A.teamName+" : "+B.teamName+"  -  "+result[0]+":"+result[1]);
            System.out.println("The winner is "+ A.teamName);
        } else if(result[0]<result[1]){
            B.numberOfMatchesWon+=1;
            B.points+=3;
            System.out.println(A.teamName+" : "+B.teamName+"  -  "+result[0]+":"+result[1]);
            System.out.println("The winner is "+B.teamName);
        } else{
            A.points+=1;
            B.points+=1;
            System.out.println(A.teamName+" : "+B.teamName+"  -  "+result[0]+":"+result[1]);
            System.out.println("The match ended in a tie.");
        }
    }
}
