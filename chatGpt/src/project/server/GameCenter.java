package project.server;

import project.config.UIController;
import project.server.mafia.ChatClientMain;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 게임을 선택할 수 있는 클래스
 * @author categorySet
 */
public class GameCenter {

    private HashMap<Integer, Status> roomPortMap;

    private UIController uiController;

    public GameCenter() {
        roomPortMap = new HashMap<>();

        uiController = new UIController();
    }

    public static int currentPort;

    public synchronized void run(Scanner scanner) {
//        System.out.println("게임 센터에 오신 걸 환영합니다.");
//        System.out.println("=== 현재 가능한 게임 ===");
//        System.out.println("1. 마피아 | 0. 종료");

        uiController.printTitle("게임 선택");
        uiController.printMenu("1.마피아, 2. 추후 업데이트 0. 종료");
        int menu = uiController.printInput(scanner);
        scanner.nextLine();

        switch (menu) {
            case 1:
                mafiaRun(scanner);
                break;
            case 0:
            default:
                break;
        }
    }

    private void mafiaRun(Scanner scanner) {
//        System.out.println("포트는 10000번부터 10004번까지 있습니다.");
//        System.out.print("포트 입력");

        String ip = null;

        try {
            DatagramSocket datagramSocket = new DatagramSocket();

            byte[] datas = "port".getBytes();

            System.out.print("아이피를 입력하세요: ");

            ip = scanner.nextLine();

            InetAddress address = InetAddress.getByName(ip);
            DatagramPacket packet = new DatagramPacket(datas, datas.length, address, 9999);

            datagramSocket.send(packet);

            byte[] data = new byte[50];
            DatagramPacket receive = new DatagramPacket(data, data.length);

            datagramSocket.receive(receive);

            currentPort = Integer.parseInt(new String(data, 0, receive.getLength()));

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int port = currentPort;

        ChatClientMain chatClientMain = new ChatClientMain(ip, port);
        chatClientMain.start();

        try {
            chatClientMain.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
