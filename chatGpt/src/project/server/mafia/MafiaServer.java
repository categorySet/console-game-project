package project.server.mafia;

import project.server.Gamable;
import project.server.Status;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 마피아 게임이 실제로 실행되는 클래스
 * @see Gamable
 * @author categorySet
 */
public class MafiaServer implements Gamable {

    public Status status = Status.Ready;

    @Override
    public void run(int port) {
//        MafiaRoom mafiaRoom = new MafiaRoom();
//
//        mafiaRoom.start();
//
//        try {
//            ServerSocket serverSocket = new ServerSocket(port);
//
//            while (true) {
//                Socket socket = serverSocket.accept();
//                  TODO socket 생성시 static 변수 ++ 후 인원수 5명 되면 방 상태 바꾸기
//                System.out.println("[소켓 연결]" + socket.getInetAddress());
//
//                ChatServerTh chatServerTh = new ChatServerTh(socket, mafiaRoom);
//
//                mafiaRoom.addClient(chatServerTh);
//
//                chatServerTh.start();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
