package project.manager;

import java.util.ArrayList;
import java.util.Scanner;

import project.item.ItemDao;
import project.player.Player;
import project.player.PlayerDao;

public class ManagerService {
    private ManagerDao managerDao;
    private ItemDao itemDao;
    private PlayerDao playerDao;

    public ManagerService() {
        managerDao = new ManagerDao();
        itemDao = new ItemDao();
        playerDao = new PlayerDao();
    }

    public void addCredit(Scanner sc) {
        System.out.println("===크레딧 부여===");

        System.out.println("크레딧을 부여할 플레이어 닉네임:");
        String nickname = sc.next();

        Player p = playerDao.findByNickname(nickname);

        if (p == null) {
            System.out.println("존재하지 않는 플레이어입니다.");
        } else {
            System.out.println("부여할 크레딧:");
            int newCredit = sc.nextInt();
            playerDao.updateCredit(p, newCredit);

            System.out.println("크레딧이 부여되었습니다.");
        }

    }

    public void subCredit(Scanner sc) {
        System.out.println("===크레딧 삭감===");

        System.out.println("크레딧을 삭감할 플레이어 닉네임:");
        String nickname = sc.next();

        Player p = playerDao.findByNickname(nickname);

        if (p == null) {
            System.out.println("존재하지 않는 플레이어입니다.");
        } else {
            System.out.println("삭감할 크레딧:");
            int newCredit = sc.nextInt();
            playerDao.updateCredit(p, -newCredit);

            System.out.println("크레딧이 삭감되었습니다.");
        }

    }

    public void printAll() {
        System.out.println("===모든 플레이어 조회===");
        ArrayList<Player> playerList = playerDao.findAll();

        for (Player p : playerList) {
            System.out.println(p);
        }
    }

    public void addToBlackList(Scanner sc) {
        System.out.println("===플레이어 블랙리스트에 추가===");

        ArrayList<Player> all = playerDao.findAll();
        for (Player player : all) {
            System.out.println(player.getPlayerId() + " | " +player.getLoginId() + " | " + player.getNickname());
        }

        System.out.println("블랙리스트에 추가될 플레이어 아이디:");        //TODO : 잘못 입력 시 존재하지 않는 유저
        int playerId = sc.nextInt();

        boolean flag = managerDao.checkBlackList(playerId);
        if (flag == true) {             //이미 밴 당한 유저가 존재한다면 밴 사유는 입력 받을 필요가 없으므로 순서를 변경함.
            System.out.println("이미 블랙리스테 오른 플레이어입니다.");
        } else {
            System.out.println("1. 욕설 2. 버그 악용 3. 게임 방해");
            System.out.println("밴 사유:");
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

//        if (flag == false) {
//            switch (m) {
//                case 1:
//                    managerDao.addBlackList(playerId, "욕설");
//                    break;
//                case 2:
//                    managerDao.addBlackList(playerId, "버그 악용");
//                    break;
//                case 3:
//                    managerDao.addBlackList(playerId, "게임 방해");
//                    break;
//            }
//            System.out.println("플레이어가 블랙리스트에 추가 되었습니다. 활동이 금지됩니다.");
//        } else {
//            System.out.println("이미 블랙리스트에 오른 플레이어입니다.");
//        }
    }

    public void printAllBlackList() {
        System.out.println("=== 블랙리스트 전체 출력===");
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
        System.out.println("===블랙리스트에서 플레이어 삭제===");

        System.out.println("밴 해제할 플레이어 아이디:");
        int playerId = sc.nextInt();

        managerDao.delBlackList(playerId);

        System.out.println("플레이어가 블랙리스트에서 삭제되었습니다.");
    }
}