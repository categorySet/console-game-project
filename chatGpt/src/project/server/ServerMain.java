package project.server;

import project.gameHistory.GameHistoryService;
import project.player.PlayerDao;
import project.server.mafia.server.MafiaServer;
import project.server.mafia.server.PortSender;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {

    private static final int NUM_OF_ROOM = 5;
    private static final int port = 10000;

    public static HashMap<Integer, ServerStarter> roomMap;
    public static ExecutorService executorService;

    private static PlayerDao playerDao = new PlayerDao();


    public static void main(String[] args) {
        GameHistoryService gameHistoryService = new GameHistoryService();

        roomMap = new HashMap<>();
        executorService = Executors.newFixedThreadPool(NUM_OF_ROOM);

        for (int i = 0; i < NUM_OF_ROOM; i++) {
            ServerStarter serverStarter = new ServerStarter(new MafiaServer(), port + i);

            executorService.execute(serverStarter);
            roomMap.put(port + i, serverStarter);
        }

        PortSender ps = new PortSender();
        ps.setDaemon(true);
        ps.start();

        while (true) {
            for (Map.Entry<Integer, ServerStarter> entry : roomMap.entrySet()) {
                if (entry.getValue().winners != null) {
                    int gameRoomId = gameHistoryService.getGameRoomId();

                    entry.getValue().winners.stream()
                            .map(w -> entry.getValue().nameAndLoginId.get(w))
                            .forEach(w -> gameHistoryService.setWinner(1, w, gameRoomId));

                    entry.getValue().winners = null;

                    PortSender.port++;
                }
            }
        }
    }

}
