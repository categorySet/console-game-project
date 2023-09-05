package gameHistory;

public class GameHistoryQueryVo {
    private int historyId;
    private String gameName;
    private String nickname;

    public GameHistoryQueryVo() {
    }

    public GameHistoryQueryVo(int historyId, String gameName, String nickname) {
        this.historyId = historyId;
        this.gameName = gameName;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "{" +
                "historyId=" + historyId +
                ", gameName='" + gameName + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
