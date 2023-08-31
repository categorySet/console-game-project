package project.server;

import project.server.mafia.server.MafiaServer;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int NUM_OF_ROOM = 5;
    private static final int port = 10000;

    public static HashMap<Integer, ServerStarter> roomMap;
    public static HashMap<Integer, Status> roomStatusMap;
    public static ExecutorService executorService;

    public static void main(String[] args) {
        roomMap = new HashMap<>();
        roomStatusMap = new HashMap<>();

        executorService = Executors.newFixedThreadPool(NUM_OF_ROOM);

        for (int i = 0; i < NUM_OF_ROOM; i++) {
            ServerStarter serverStarter = new ServerStarter(new MafiaServer(), port + i);

            executorService.execute(serverStarter);
            roomMap.put(10000 + i, serverStarter);
            roomStatusMap.put(10000 + i, ((MafiaServer) serverStarter.getGamable()).getStatus());
        }

    }

}
