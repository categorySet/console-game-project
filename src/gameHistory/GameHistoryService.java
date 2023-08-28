package gameHistory;

import java.util.List;

public class GameHistoryService {

    private GameHistoryDao dao;

    public GameHistoryService() {
        this.dao = new GameHistoryDao();
    }

    public int setWinner(int gameId, int playerId) {
        return dao.insert(gameId, playerId);
    }

    public List<GameHistoryQueryVo> getMyHistory(int playerId) {
        return dao.findByPlayerId(playerId);
    }

}
