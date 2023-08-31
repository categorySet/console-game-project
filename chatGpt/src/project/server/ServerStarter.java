package project.server;

import gameHistory.GameHistoryService;
import project.player.PlayerService;

import java.util.List;

public class ServerStarter extends Thread {

    private GameHistoryService gameHistoryService;

    private Gamable gamable;
    private int port;

    public static List<String> winners;

    public ServerStarter(Gamable gamable, int port) {
        this.gamable = gamable;
        this.port = port;

        gameHistoryService = new GameHistoryService();
    }

    public Gamable getGamable() {
        return gamable;
    }

    @Override
    public void run() {
        gamable.run(port);

        while (winners == null) {

        }
        winners.forEach(w -> gameHistoryService.setWinner(1, w));
    }

}
