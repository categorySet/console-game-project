package server.mafia.server;

import server.Gamable;
import server.ServerStarter;
import server.Status;
import server.mafia.room.MafiaRoom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

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
            serverSocket.setSoTimeout(5000);

            Socket socket = null;
            while (!serverStarter.isInterrupted()) {
                try {
                    socket = serverSocket.accept();
                } catch (SocketTimeoutException e) {
                    continue;
                }

                if (connectCounter >= mafiaRoom.list.size()) {
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
