package project.server.mafia.server;

import project.server.Gamable;
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

    private Status status;
    private int connectCounter;
    private MafiaRoom mafiaRoom;

    public MafiaServer() {
        this.status = Status.READY;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setMafiaRoom(MafiaRoom mafiaRoom) {
        this.mafiaRoom = mafiaRoom;
    }

    @Override
    public void run(int port) {
        mafiaRoom = new MafiaRoom(this);

        mafiaRoom.start();

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept();

                connectCounter++;

                if (connectCounter >= 5) {
                    status = Status.GAMING;
                }

                System.out.println("[소켓 연결]" + socket.getInetAddress());

                ChatServerTh chatServerTh = new ChatServerTh(socket, mafiaRoom);

                mafiaRoom.addClient(chatServerTh);

                chatServerTh.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
