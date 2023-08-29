package project.server;

import project.server.mafia.server.MafiaServer;

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

    public static HashMap<Integer, ServerStarter> roomMap;
    public static HashMap<Integer, Status> roomPortMap;
    public static ExecutorService executorService;

    private static final int NUM_OF_ROOM = 5;


    public GameCenter() {
        roomMap = new HashMap<>();
        roomPortMap = new HashMap<>();

        executorService = Executors.newFixedThreadPool(NUM_OF_ROOM);

        for (int i = 0; i < NUM_OF_ROOM; i++) {
            executorService.execute(new ServerStarter(new MafiaServer(), 10000 + i));
        }
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

    public void mafiaRun(Scanner scanner) {
        System.out.println("=== 현재 방 목록 === ");
        for (Map.Entry<Integer, Status> m: roomPortMap.entrySet()) {
            if (m.getValue() == Status.Ready) {
                System.out.print(m.getKey() + " ");
            }
        }

    }

}
