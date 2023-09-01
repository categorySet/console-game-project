package project.gameHistory;

import project.player.Player;
import project.player.PlayerDao;

import java.util.List;

public class GameHistoryService {

    private static final int winnerPoint = 50;

    private GameHistoryDao historyDao;
    private PlayerDao playerDao;

    public GameHistoryService() {
        this.historyDao = new GameHistoryDao();
        this.playerDao = new PlayerDao();
    }

    public int getGameRoomId() {
        return historyDao.getGameRoomId();
    }

    public int getSequenceNumber() {
        return historyDao.getGameRoomId();
    }

    public int setWinner(int gameId, int playerId, int sequenceNumber) {
        return historyDao.insert(gameId, playerId, sequenceNumber);
    }

    public synchronized int setWinner(int gameId, String nickname, int sequenceNumber) {
        Player player = playerDao.findByNickname(nickname);

        playerDao.updateCredit(player, 50);

        return historyDao.insert(gameId, player.getPlayerId(), sequenceNumber);
    }

    public void getMyHistory(String nickname) {
        for (GameHistoryQueryVo vo : historyDao.findByPlayerId(nickname)) {
            System.out.println(vo);
        }
    }


}
