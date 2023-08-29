package project.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 게임을 선택할 수 있는 클래스
 * @author categorySet
 */
public class GameCenter {

    public static final HashMap<Integer, ServerStarter> roomMap = new HashMap<>();
    public static final HashMap<Integer, Status> roomPortMap = new HashMap<>();

    private static final int NUM_OF_ROOM = 5;

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
                System.out.print("포트 번호: " + m.getKey() + " ");

            }
        }


    }

}
