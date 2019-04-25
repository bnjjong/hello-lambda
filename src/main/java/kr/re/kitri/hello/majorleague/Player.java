package kr.re.kitri.hello.majorleague;

import lombok.Data;

@Data
public class Player {
    private int year;
    private String team;
    private String league;
    private String player;
    private long salary;

    public Player(int year, String team, String league, String player, long salary) {
        this.year = year;
        this.team = team;
        this.league = league;
        this.player = player;
        this.salary = salary;
    }
}
