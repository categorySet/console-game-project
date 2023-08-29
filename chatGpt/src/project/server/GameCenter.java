package project.server;

import project.server.mafia.ChatClientMain;
import project.server.mafia.server.MafiaServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 게임을 선택할 수 있는 클래스
 * @author categorySet
 */
public class GameCenter {

    private HashMap<Integer, Status> roomPortMap;

    public GameCenter() {
        roomPortMap = new HashMap<>();
    }

    public synchronized void run(Scanner scanner) {
        System.out.println("게임 센터에 오신 걸 환영합니다.");
        System.out.println("=== 현재 가능한 게임 ===");
        System.out.println("1. 마피아 | 0. 종료");

        int menu = scanner.nextInt();
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
        System.out.println("포트는 10000번부터 10004번까지 있습니다.");
        System.out.print("포트 입력");
        int port = scanner.nextInt();

        ChatClientMain chatClientMain = new ChatClientMain("localhost", port);
        chatClientMain.executeChatClient();

        synchronized (Thread.currentThread()) {
            try {
                Thread.currentThread().wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
