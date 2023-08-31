package project;

import gameHistory.GameHistoryService;
import project.admin.AdminService;
import project.item.ItemService;
import project.player.PlayerService;
import project.server.GameCenter;
import project.shop.OrderService;

import java.util.Scanner;

public class Menu {
    private PlayerService playerService;
    private OrderService orderService;
    private ItemService itemService;
    private GameHistoryService gameHistoryService;
    private AdminService adminService;

    public Menu() {
        playerService = new PlayerService();
        orderService = new OrderService();
        itemService = new ItemService();
        gameHistoryService = new GameHistoryService();
        adminService = new AdminService();
    }

    public void run(Scanner sc) {
        System.out.println("*************************");
        System.out.println("=========================");
        System.out.println("Chat GPT (Game Play Time)");
        System.out.println("=========================");
        System.out.println("*************************");

        boolean flag = true;
        int m = 0;
        while (flag) {
            String menu = "1.로그인 2.회원가입 3.회원탈퇴 0.종료";
            if (!playerService.isLogin()) {
                System.out.println(menu);
                System.out.print("숫자를 입력하세요 : ");
                m = sc.nextInt();

                switch (m) {
                    case 0:
                        flag = false;
                        System.out.println("종료합니다");
                        break;
                    case 1:
                        playerService.login(sc);
                        if (playerService.isLogin()) {
                            flag = false;
                        }
                        break;
                    case 2:
                        playerService.signup(sc);
                        break;
                    case 3:
                        playerService.deletePlayer(sc);
                        break;
                }
            }

            if (playerService.isLogin()) {
                mainRun(sc);
            }
            if (m == 0 && !playerService.isLogin()) {
                flag = false;
            } else if (!playerService.isLogin()) {
                flag = true;
            }
        }

    }

    private void mainRun(Scanner sc) {
        if (playerService.isLogin()) {
            String menu = "1.게임 2.상점 3.내 정보 4.내 정보 수정 0.로그아웃";
            int m = 0;

            while (playerService.isLogin()) {
                System.out.println("-------------------------");
                System.out.println(menu);
                System.out.println("-------------------------");

                System.out.print("숫자를 입력하세요 : ");
                m = sc.nextInt();
                switch (m) {
                    case 0:
                        System.out.println("로그아웃.");
                        playerService.logout();
                        break;
                    case 1:
                        gameRun(sc);
                        break;
                    case 2:
                        shopRun(sc);
                        break;
                    case 3:
                        playerService.printMyInfo();
                        break;
                    case 4:
                        myInfoRun(sc);
                        break;
                }
            }
        }
    }

    private void myInfoRun(Scanner sc) {
        System.out.println("정보 수정");
        String menu = "1. 닉네임 변경 2. 비밀번호 변경 0.뒤로 가기";

        boolean flag = true;
        int m = 0;

        while (flag) {
            System.out.println("---------------------------------------------------------");
            System.out.println(menu);
            System.out.println("----------------------------------------------------------");

            System.out.print("번호를 입력해주세요.");
            m = sc.nextInt();

            switch (m) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    playerService.updateNickname(sc);
                    break;
                case 2:
                    playerService.updatePassword(sc);
                    break;
            }
        }
    }

    private void gameRun(Scanner sc) {
        GameCenter gameCenter = new GameCenter();
        String menu = "1. 채팅 게임 2. 전적 확인";
        boolean flag = true;
        int m = 0;

        while (flag) {
            System.out.println("---------------------------------------------------------");
            System.out.println(menu);
            System.out.println("----------------------------------------------------------");

            System.out.print("번호를 입력해주세요.");
            m = sc.nextInt();

            switch (m) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    gameCenter.run(sc);
                    break;
                case 2:
                    break;
            }
        }
    }

    private void shopRun(Scanner sc) {
        String menu = "1.아이템구매 2.구매내역 3.아이템등록 4.조회 5.수정 6.삭제 0.종료";
        boolean flag = true;
        int m = 0;

        while (flag) {
            System.out.println("---------------------------------------------------------");
            System.out.println(menu);
            System.out.println("---------------------------------------------------------");
            System.out.print("번호를 입력해주세요.");
            m = sc.nextInt();

            switch (m) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    orderService.addOrder(sc, itemService.getAll());
                    break;
                case 2:
                    break;
                // FIXME: item test
                case 3:
                    itemService.addItem(sc);
                    break;
                case 4:
                    itemService.printAll();
                    break;
                case 5:
                    itemService.editItem(sc);
                    break;
                case 6:
                    itemService.deleteItem(sc);
                    break;
            }
        }
    }

}
