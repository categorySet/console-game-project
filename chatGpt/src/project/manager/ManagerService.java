package project.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project.config.UIController;
import project.item.Item;
import project.item.ItemDao;
import project.player.Player;
import project.player.PlayerDao;

public class ManagerService {
    private ManagerDao managerDao;
    private ItemDao itemDao;
    private PlayerDao playerDao;
    private UIController uiController;

    public ManagerService() {
        managerDao = new ManagerDao();
        itemDao = new ItemDao();
        playerDao = new PlayerDao();
        uiController = new UIController();
    }

    public void addCredit(Scanner sc) {
        uiController.printSubTitle("크레딧 부여");
        System.out.print("크레딧을 부여할 플레이어 닉네임 : ");
        String nickname = sc.next();

        Player p = playerDao.findByNickname(nickname);

        if (p == null) {
            System.out.println("존재하지 않는 플레이어입니다.");
        } else {
            System.out.print("부여할 크레딧 : ");
            int newCredit = sc.nextInt();
            playerDao.updateCredit(p, newCredit);

            System.out.println("크레딧이 부여되었습니다.");
        }

    }

    public void subCredit(Scanner sc) {
        uiController.printSubTitle("크레딧 삭감");

        System.out.print("크레딧을 삭감할 플레이어 닉네임:");
        String nickname = sc.next();

        Player p = playerDao.findByNickname(nickname);

        if (p == null) {
            System.out.println("존재하지 않는 플레이어입니다.");
        } else {
            System.out.print("삭감할 크레딧 : ");
            int newCredit = sc.nextInt();
            playerDao.updateCredit(p, -newCredit);

            System.out.println("크레딧이 삭감되었습니다.");
        }

    }

    public void printAll() {
        uiController.printSubTitle("모든 플레이어 조회");
        ArrayList<Player> playerList = playerDao.findAll();

        printAllPlayer(playerList);
    }

    public void printAllPlayer(ArrayList<Player> players) {
        System.out.printf("%-10s %-15s %-15s %-15s %-15s %n", "번호", "이름", "닉네임", "크레딧", "가입일");
        if (players.isEmpty()) {
            System.out.println("플레이어가 존재하지 않습니다.");
        }

        for (Player player : players) {
            System.out.printf("%-10s %-15s %-15s %-15s %-15s %n", player.getPlayerId(), player.getLoginId(), player.getNickname(), player.getCredit(), player.getCreateDate());
        }
    }

    public void addToBlackList(Scanner sc) {
        System.out.println("===플레이어 블랙리스트에 추가===");

        ArrayList<Player> all = playerDao.findAll();
        printAllPlayer(all);

        System.out.print("블랙리스트에 추가될 플레이어 번호 :");
        int playerId = sc.nextInt();

        boolean flag = managerDao.checkBlackList(playerId);
        if (flag == true) {                                      //이미 밴 당한 유저가 존재한다면 밴 사유는 입력 받을 필요가 없으므로 순서를 변경함.
            System.out.println("이미 블랙리스트에 오른 플레이어입니다.");
        } else {
            System.out.println("1. 욕설 2. 버그 악용 3. 게임 방해");
            System.out.print("밴 사유 : ");
            int m = sc.nextInt();

            switch (m) {
                case 1:
                    managerDao.addBlackList(playerId, "욕설");
                    break;
                case 2:
                    managerDao.addBlackList(playerId, "버그 악용");
                    break;
                case 3:
                    managerDao.addBlackList(playerId, "게임 방해");
                    break;
            }
            System.out.println("플레이어가 블랙리스트에 추가 되었습니다. 활동이 금지됩니다.");
        }
    }

    public void printAllBlackList() {
        uiController.printSubTitle("블랙리스트 전체 조회");
        ArrayList<BlackList> list = managerDao.selectAllBlackList();

        if (list.isEmpty() || list == null) {
            System.out.println("블랙리스트에 추가된 플레이어가 없습니다.");
        } else {
            for (BlackList b : list) {
                System.out.println(b);
            }
        }
    }

    public void delFromBlackList(Scanner sc) {
        uiController.printSubTitle("블랙리스트 플레이어 삭제");

        System.out.print("블랙리스트에서 삭제할 플레이어 번호 : ");
        int playerId = sc.nextInt();

        managerDao.delBlackList(playerId);

        System.out.println("플레이어가 블랙리스트에서 삭제되었습니다.");
    }
}