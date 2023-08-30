package project;

import gameHistory.GameHistoryService;
import project.admin.AdminService;
import project.item.ItemService;
import project.player.PlayerService;
import project.server.GameCenter;
import project.shop.ShopService;

import java.util.Scanner;

public class Menu {
    private PlayerService playerService;
    private ShopService shopService;
    private ItemService itemService;
    private GameHistoryService gameHistoryService;
    private AdminService adminService;

    public Menu() {
        playerService = new PlayerService();
        shopService = new ShopService();
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

        if(!playerService.isLogin()) {
            loginRun(sc);
        }

        if(playerService.isLogin()) {
            String menu = "1.게임 2.상점 3.내정보 0.종료";
            boolean flag = true;
            int m = 0;

            while (flag) {
                System.out.println("-------------------------");
                System.out.println(menu);
                System.out.println("-------------------------");

                System.out.print("숫자를 입력하세요 : ");
                m = sc.nextInt();
                switch (m) {
                    case 0:
                        System.out.println("종료합니다.");
                        flag = false;
                        break;
                    case 1:
                        //TODO : GameCenter
                        gameRun(sc);
                        break;
                    case 2:
                        //TODO : Shop
                        srun(sc);
                        break;
                    case 3:
                        //TODO : MyInfo?
                        break;
                }
            }
        }
    }

    private void loginRun(Scanner sc) {
        String menu = "1.로그인 2.회원가입 0.종료";
        boolean flag = true;
        int m = 0;

        while (flag) {
            System.out.println(menu);

            System.out.print("숫자를 입력하세요 : ");
            m = sc.nextInt();

            switch (m) {
                case 0:
                    flag = false;
                    System.out.println("종료합니다");
                    break;
                case 1:
                    playerService.testLogin();          //실제 로그인 로직으로 교체
                    if(playerService.isLogin()) {       //query 한번으로 처리하는 방법 찾기
                        flag = false;
                    }
                    break;
                case 2:
                    playerService.signup(sc);
                    break;
                case 9999:                              //Admin 담당과 생각해보기
//                    adminService(sc);
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

    private void srun(Scanner sc) {
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
                    flag= false;
                    break;
                case 1:
                    shopService.addShop(sc, itemService.getAll());
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
