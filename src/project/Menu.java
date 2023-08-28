package project;

import project.player.PlayerService;
import project.server.GameCenter;
import project.shop.ShopService;

import java.util.Scanner;

public class Menu {
    private PlayerService playerService;
    private ShopService shopService;
    private GameCenter gameCenter;

    public Menu() {
        playerService = new PlayerService();
        shopService = new ShopService();
        gameCenter = new GameCenter();
    }

    public void run(Scanner sc) {
        System.out.println("*************************");
        System.out.println("=========================");
        System.out.println("Chat GPT (Game Play Time)");
        System.out.println("=========================");
        System.out.println("*************************");



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
                    grun(sc);
                    break;
                case 2:
                    //TODO : Shop
                    srun(sc);
                    break;
                case 3:
                    //TODO : MyInfo
                    break;
            }
        }
    }

    //TODO : 나중에 사용할 것
    private void lrun(Scanner sc) {
        String menu = "1.로그인 2.회원가입 0.종료";
        boolean flag = true;
        int m = 0;

        while (flag) {
            System.out.println("------------------------유저-------------------------------");
            System.out.println(menu);
            System.out.println("----------------------------------------------------------");

            System.out.print("숫자를 입력하세요 : ");
            m = sc.nextInt();

            switch (m) {
                case 0:
                    flag = false;
                    System.out.println("종료합니다");
                    break;
                case 1:
//                    playerService.login();
                    break;
                case 2:
//                    playerService.signup(sc);
                    break;
                case 9999:
//                    adminService(sc);
                    break;
            }
        }
    }



    private void grun(Scanner sc) {
        String menu = "1.게임에 관련된 항목들을 작성할 것";
        boolean flag = true;
        int m = 0;

        while (flag) {
            System.out.println("------------------------유저-------------------------------");
            System.out.println(menu);
            System.out.println("----------------------------------------------------------");

            System.out.print("숫자를 입력하세요 : ");
            m = sc.nextInt();

            switch (m) {
                case 0:
                    flag = false;
                    break;
                case 1:
//                    gameCenter
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }
        }
    }

    private void srun(Scanner sc) {
        String menu = "Shop에 관련된 항목들을 작성할 것";
        boolean flag = true;
        int m = 0;

        while (flag) {
            System.out.println(menu);
            m = sc.nextInt();

            switch (m) {
                case 0:
                    flag= false;
                    break;
                case 1:
//                    shopService
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
            }
        }
    }






}
