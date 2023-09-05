package project.server;

import project.gameHistory.GameHistoryService;

import java.util.Hashtable;
import java.util.List;

public class ServerStarter extends Thread {

    private GameHistoryService gameHistoryService;

    private Gamable gamable;
    private int port;

    public Status status;

    public List<String> winners;

    public Hashtable<String, String> nameAndLoginId = new Hashtable<>();
    public Hashtable<String, String> fullNicknames = new Hashtable<>();

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
        int gameStatus = gamable.run(port, this);

        System.out.println("[Info] ServerStarter.run 종료됨 상태코드: " + gameStatus);
    }

}
