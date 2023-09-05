package server.mafia.server;

import server.ServerStarter;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


public class RoomManager extends Thread {

    private ConcurrentHashMap<Integer, ServerStarter> roomMap;
    private Thread thread;
    private int port;

    public RoomManager(ConcurrentHashMap roomMap, Thread thread, int port) {
        this.roomMap = roomMap;
        this.thread = thread;
        this.port = port;
    }

    @Override
    public void run() {
        while (true) {
            if (!thread.isAlive()) {
                roomMap.remove(port);

                ServerStarter newServerStarter = new ServerStarter(new MafiaServer(), port);

                newServerStarter.start();

                thread = newServerStarter;

                roomMap.put(port, newServerStarter);
            }
        }
    }
}
