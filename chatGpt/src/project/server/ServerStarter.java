package project.server;

public class ServerStarter extends Thread{
    private Gamable gamable;
    private int port;

    public ServerStarter(Gamable gamable, int port) {
        this.gamable = gamable;
        this.port = port;
    }

    public Gamable getGamable() {
        return gamable;
    }

    @Override
    public void run() {
        gamable.run(port);
    }
}
