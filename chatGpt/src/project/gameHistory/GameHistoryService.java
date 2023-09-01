package project.gameHistory;

import project.player.Player;
import project.player.PlayerDao;

import java.util.List;

public class GameHistoryService {

    private static final int winnerPoint = 50;

    private GameHistoryDao dao;
    private PlayerDao playerDao;

    public GameHistoryService() {
        this.dao = new GameHistoryDao();
        this.playerDao = new PlayerDao();
    }

    public int setWinner(int gameId, int playerId) {
        return dao.insert(gameId, playerId);
    }

    public synchronized int setWinner(int gameId, String nickname) {
        Player player = playerDao.findByNickname(nickname);

        playerDao.updateCredit(player, 50);

        return dao.insert(gameId, player.getPlayerId());
    }

    public List<GameHistoryQueryVo> getMyHistory(String nickname) {
        return dao.findByPlayerId(nickname);
    }


}
