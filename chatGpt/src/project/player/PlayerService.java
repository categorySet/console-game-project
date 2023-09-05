package project.player;

import java.util.List;
import java.util.Scanner;

import project.Menu;
import project.config.UIController;
import project.item.Item;
import project.item.ItemDao;
import project.manager.ManagerDao;

public class PlayerService {
    public static String loginId = null;
    public static String nickname;
    public static String fullNickname;
    private PlayerDao playerDao;
    private ItemDao itemDao;
    private ManagerDao managerDao;
    private UIController uiController;

    public PlayerService() {
        this.playerDao = new PlayerDao();
        this.itemDao = new ItemDao();
        this.managerDao = new ManagerDao();
        this.uiController = new UIController();
    }

    public boolean isLogin() {
        if(this.loginId != null) {
            return true;
        }
        return false;
    }

    public static String getLoginId() {
        return loginId;
    }

    public static String getNickname () {
        return nickname;
    }

    public static String getFullNickname() {
        return fullNickname;
    }

    public void signup(Scanner sc) {
        System.out.println("회원가입");
        System.out.print("아이디를 입력해주세요 : ");
        String loginId = sc.next();
        System.out.print("비밀번호를 입력해주세요 : ");
        String password = sc.next();
        System.out.print("비밀번호 확인을 입력해주세요 : ");
        String confirmPassword = sc.next();

        System.out.print("닉네임을 입력해주세요 : ");
        String nickname = sc.next();

        playerDao.signup(new Player(loginId, password, confirmPassword, nickname));
    }

    public void login(Scanner sc) {
        uiController.printSubTitle("로그인");
        System.out.print("아이디를 입력해주세요 : ");
        String loginId = sc.next();
        System.out.print("비밀번호를 입력해주세요 : ");
        String password = sc.next();
        Player findPlayer = playerDao.findByLoginId(loginId);


        if(findPlayer != null) {
            if(managerDao.checkBlackList(findPlayer.getPlayerId())) {
                System.out.println("차단된 유저입니다.");
            } else if (findPlayer.getPassword().equals(password)) {
                this.loginId = loginId;
                this.nickname = findPlayer.getNickname();
                this.fullNickname = findPlayer.getNickname();
                uiController.printTitle(nickname + "님 환영합니다.");

            } else {
                System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
            }
        } else {
            System.out.println("존재하지 않는 유저입니다.");
        }
    }

    public void logout() {
        uiController.printTitle("로그아웃");
        this.loginId = null;
    }

    public void printMyInfo() {
        Player findPlayer = playerDao.findByLoginId(loginId);
        uiController.printSubTitle("내 정보");
        System.out.println("아이디 : " + findPlayer.getLoginId());
        System.out.println("닉네임 : " + findPlayer.getNickname());
        System.out.println("보유 크레딧 : " + findPlayer.getCredit());
        System.out.println("가입일 : " + findPlayer.getCreateDate());
    }


    public void updateNickname(Scanner sc) {
        Player findPlayer = playerDao.findByLoginId(loginId);
        if (findPlayer != null) {
            uiController.printSubTitle("닉네임 변경");
            System.out.print("변경할 닉네임을 입력해주세요 : ");
            String newNickname = sc.next();

            playerDao.updateNickname(new Player(findPlayer.getPlayerId(), newNickname));

        } else {
            System.out.println("존재하지 않는 회원입니다.");
        }
    }

    public void updatePassword(Scanner sc) {
        Player findPlayer = playerDao.findByLoginId(loginId);
        if (findPlayer != null) {
            uiController.printSubTitle("비밀번호 변경");
            System.out.print("현재 비밀번호를 입력해주세요 : ");
            String oldPwd = sc.next();
            if (playerDao.validatePwd(findPlayer.getLoginId(), oldPwd)) {
                System.out.print("새로운 비밀번호를 입력해주세요 : ");
                String newPwd = sc.next();
                System.out.print("비밀번호 확인을 입력해주세요 : ");
                String confirmPwd = sc.next();

                playerDao.updatePassword(new Player(findPlayer.getPlayerId(), newPwd, confirmPwd));

            } else {
                System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
            }
        }
    }

    public void updateCreditByNickname(String nickname, int newCredit) {
        Player findPlayer = playerDao.findByNickname(nickname);

        if (findPlayer != null) {
            playerDao.updateCredit(findPlayer, newCredit);
        } else {
            System.out.println("존재하지 않는 회원입니다.");
        }
    }

    public void deletePlayer(Scanner sc) {
        uiController.printSubTitle("회원 탈퇴");
        System.out.print("아이디를 입력해주세요 : ");
        String loginId = sc.next();

        Player findPlayer = playerDao.findByLoginId(loginId);

        if(findPlayer != null) {
            System.out.print("비밀번호를 입력해주세요 : ");
            String password = sc.next();
            if (playerDao.validatePwd(findPlayer.getLoginId(), password)) {
                System.out.println("정말 삭제하시겠습니까 ? (Y/N)");
                String confirm = sc.next();
                if(confirm.toUpperCase().equals("Y")) {
                    playerDao.delete(findPlayer);
                    System.out.println("유저가 삭제되었습니다.");
                    System.out.println("이용해주셔서 감사합니다.");
                }

            } else {
                System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
            }

        } else {
            System.out.println("존재하지 않는 회원입니다.");
        }
    }

    public Item useItem(List<Item> itemList, Scanner sc) {
        Item result = new Item();
        int m = 0;
        uiController.printSubTitle("아이템 장착");
        m = uiController.printInput(sc);

        for (Item item : itemList) {
            if(m == item.getItemId()) {
                result = item;
            }
        }

        if(result.getItemName() != null) {
            return result;
        } else {
            return null;
        }
    }
}
