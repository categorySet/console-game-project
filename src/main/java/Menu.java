import config.UIController;
import gameHistory.GameHistoryService;
import item.Item;
import item.ItemService;
import manager.ManagerService;
import player.PlayerService;
import server.GameCenter;
import shop.PerchaseService;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private PlayerService playerService;
    private PerchaseService perchaseService;
    private ItemService itemService;
    private GameHistoryService gameHistoryService;
    private ManagerService managerService;
    private UIController uiController;

    public Menu() {
        playerService = new PlayerService();
        perchaseService = new PerchaseService();
        itemService = new ItemService();
        gameHistoryService = new GameHistoryService();
        managerService = new ManagerService();
        uiController = new UIController();
    }

    public void run(Scanner sc) {
        boolean flag = true;
        int m = 0;
        while (flag) {
            uiController.printTitle("Chat GPT (Game Play Time)");

            if (!playerService.isLogin()) {
                String menu = "1.로그인 2.회원 가입 3.회원 탈퇴 0.종료";
                uiController.printMenu(menu);
                m = uiController.printInput(sc);

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
                    case 9876:
                        managerRun(sc);
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
                uiController.printMenu(menu);
                m = uiController.printInput(sc);

                switch (m) {
                    case 0:
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
        String menu = "1. 닉네임 변경 2. 비밀번호 변경 3. 아이템 장착 0.뒤로 가기";

        boolean flag = true;
        int m = 0;

        while (flag) {
            uiController.printMenu(menu);
            m = uiController.printInput(sc);

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
                case 3:
                    List<Item> itemIdList = perchaseService.printPurchaseById();
                    Item item = playerService.useItem(itemIdList, sc);
                    perchaseService.applyItemToNickname(item);
                    break;
            }
        }
    }

    private void gameRun(Scanner sc) {
        GameCenter gameCenter = new GameCenter();
        uiController.printTitle("Game Center");
        String menu = "1.채팅 게임 2.전적 확인 | 0. 종료";

        boolean flag = true;
        int m = 0;

        while (flag) {
            uiController.printMenu(menu);
            m = uiController.printInput(sc);

            switch (m) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    gameCenter.run(sc);
                    break;
                case 2:
                    gameHistoryService.getMyHistory(PlayerService.getLoginId());
                    break;
            }
        }
    }

    private void shopRun(Scanner sc) {
        sc.nextLine();
        String menu = "1.아이템 구매 2.구매 내역 0.뒤로 가기";
        boolean flag = true;
        int m = 0;

        while (flag) {
            uiController.printTitle("상점");
            uiController.printMenu(menu);
            m = uiController.printInput(sc);

            switch (m) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    perchaseService.addPurchase(sc, itemService.getAll());
                    break;
                case 2:
                    perchaseService.printPurchaseById();
                    break;
            }
        }
    }

    private void managerRun(Scanner sc) {
        boolean flag = true;

        uiController.printTitle("관리자 페이지");
        String menu = "1.크레딧 부여 2.크레딧 삭감 3.모든 플레이어 조회";
        String menu2 = "4.블랙리스트 추가 5.블랙리스트 조회 6.블랙리스트 삭제";
        String menu3 = "7.아이템 등록 8.아이템 조회 9.아이템 삭제 0.뒤로가기";

        int m = 0;
        while (flag) {
            String line = "=".repeat(uiController.getWIDTH());
            System.out.printf(line +"%n");
            System.out.printf("%s%n", menu);
            uiController.printSubLine();
            System.out.printf("%s%n", menu2);
            uiController.printSubLine();
            System.out.printf("%s%n", menu3);

            System.out.printf(line + "%n");
            m = uiController.printInput(sc);

            switch (m) {
                case 1:
                    managerService.printAllPlayer();
                    managerService.addCredit(sc);
                    break;
                case 2:
                    managerService.printAllPlayer();
                    managerService.subCredit(sc);
                    break;
                case 3:
                    managerService.printAllPlayer();
                    break;
                case 4:
                    managerService.addToBlackList(sc);
                    break;
                case 5:
                    managerService.printAllPlayer();
                    managerService.printAllBlackList();
                    break;
                case 6:
                    managerService.printAllBlackList();
                    managerService.delFromBlackList(sc);
                    break;
                case 7:
                    itemService.addItem(sc);
                    break;
                case 8:
                    itemService.printAll();
                    break;
                case 9:
                    itemService.printAll();
                    itemService.deleteItem(sc);
                    break;
                case 0:
                    flag = false;
                    break;
            }
        }
    }
}
