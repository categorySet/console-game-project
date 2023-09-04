package project.server.mafia.server;

import project.server.Gamable;
import project.server.ServerStarter;
import project.server.Status;
import project.server.mafia.room.MafiaRoom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 마피아 게임이 실제로 실행되는 클래스
 * @see Gamable
 * @author categorySet
 */
public class MafiaServer implements Gamable {

    private ServerStarter serverStarter;
    private int connectCounter;
    private MafiaRoom mafiaRoom;

    public void setMafiaRoom(MafiaRoom mafiaRoom) {
        this.mafiaRoom = mafiaRoom;
    }

    @Override
    public int run(int port, ServerStarter serverStarter) {
        mafiaRoom = new MafiaRoom(this, serverStarter);

        mafiaRoom.start();

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (!serverStarter.isInterrupted()) {
                Socket socket = serverSocket.accept();

                connectCounter++;

                if (connectCounter >= 5) {
                    serverStarter.status = Status.GAMING;
                }

                System.out.println("[소켓 연결]" + socket.getInetAddress());

                ChatServerTh chatServerTh = new ChatServerTh(socket, mafiaRoom, serverStarter);

                mafiaRoom.addClient(chatServerTh);

                chatServerTh.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("[Info] MafiaServer.run 종료됨");

        mafiaRoom.interrupt();

        return 0;
    }

}
