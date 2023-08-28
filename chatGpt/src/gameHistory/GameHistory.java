package gameHistory;

import java.sql.Date;

public class GameHistory {
    private int historyId;
    private int gameId;
    private int winner;
    private Date createDate;
    private Date lastModifiedDate;

    public GameHistory() {
    }

    public GameHistory(int historyId, int gameId, int winner) {
        this.historyId = historyId;
        this.gameId = gameId;
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "GameHistory{" +
                "historyId=" + historyId +
                ", gameId=" + gameId +
                ", winner=" + winner +
                ", createDate=" + createDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
