package project.player;

import java.util.Scanner;

public class PlayerService {
    private static String loginId = null;
    private PlayerDao playerDao;

    public PlayerService() {
        this.playerDao = new PlayerDao();
    }

    public boolean isLogin() {
        return (this.loginId != null) ? true : false;
    }

    public static String getLoginId() {
        return loginId;
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
        System.out.println("로그인");
        System.out.print("아이디를 입력해주세요 : ");
        String loginId = sc.next();
        System.out.print("비밀번호를 입력해주세요 : ");
        String password = sc.next();
        Player findPlayer = playerDao.findByLoginId(loginId);

        if (findPlayer != null) {
            if (findPlayer.getPassword().equals(password)) {
                this.loginId = loginId;
                System.out.println(findPlayer.getNickname() + "님 환영합니다.");

            } else {
                System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
            }
        } else {
            System.out.println("존재하지 않는 유저입니다.");
        }
    }

    public void logout() {
        this.loginId = null;
    }

    public void printMyInfo() {
        Player findPlayer = playerDao.findByLoginId(loginId);
        System.out.println("==== 내 정보 ====");
        System.out.println("아이디 : " + findPlayer.getLoginId());
        System.out.println("닉네임 : " + findPlayer.getNickname());
        System.out.println("보유 크레딧 : " + findPlayer.getCredit());
        System.out.println("가입일 : " + findPlayer.getCreateDate());
    }


    public void updateNickname(Scanner sc) {
        Player findPlayer = playerDao.findByLoginId(loginId);
        if (findPlayer != null) {
            System.out.println("닉네임 변경");
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
            System.out.println("비밀번호 변경");
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

    public void deletePlayer(Scanner sc) {
        System.out.println("계정 삭제");
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
}
