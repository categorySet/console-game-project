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
    }

    public int setWinner(int gameId, int playerId) {
        return dao.insert(gameId, playerId);
    }

    public int setWinner(int gameId, String nickname) {
        Player player = playerDao.findByNickname(nickname);

        System.out.println("GameHistoryService.setWinner " + player.getPlayerId() + " " + player.getCredit());

        playerDao.updateCredit(player, 50);

        System.out.println("GameHistoryService.setWinner " + player.getPlayerId() + " " + player.getCredit());

        return dao.insert(gameId, player.getPlayerId());
    }

    public List<GameHistoryQueryVo> getMyHistory(String nickname) {
        return dao.findByPlayerId(nickname);
    }


}
