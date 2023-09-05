package server;

import gameHistory.GameHistoryService;
import player.PlayerDao;
import server.mafia.server.MafiaServer;
import server.mafia.server.PortSender;
import server.mafia.server.RoomManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerMain {

    private static final int NUM_OF_ROOM = 5;
    private static final int port = 10000;

    public static ConcurrentHashMap<Integer, ServerStarter> roomMap;

    private static PlayerDao playerDao = new PlayerDao();


    public static void main(String[] args) {
        GameHistoryService gameHistoryService = new GameHistoryService();

        roomMap = new ConcurrentHashMap<>();

        for (int i = 0; i < NUM_OF_ROOM; i++) {
            ServerStarter serverStarter = new ServerStarter(new MafiaServer(), port + i);

            serverStarter.start();

            roomMap.put(port + i, serverStarter);

            RoomManager roomManager = new RoomManager(roomMap, serverStarter, port + i);

            roomManager.start();
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

                    entry.getValue().interrupt();
                }
            }

        }
    }

}
