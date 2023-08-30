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
    public void testLogin() {
        this.loginId = "testPlayer";
    }
    public String checkLogin() {
        return this.loginId;
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

        playerDao.signup(loginId, password, confirmPassword, nickname);


//        //TODO : 비밀번호 확인을 sevice에서 처리할 지, dao에서 처리할 지 고민해보기
//        //Dao에 따로 생성하고 service에서 처리
//        if (!password.equals(confirmPassword)) {
//            System.out.println("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
//        } else {
//            System.out.print("닉네임을 입력해주세요 : ");
//            String nickname = sc.next();
//        }
    }

    public void login(Scanner sc) {

        System.out.println("로그인");
        System.out.print("아이디를 입력해주세요 : ");
        String loginId = sc.next();
        System.out.print("비밀번호를 입력해주세요 : ");
        String password = sc.next();

        Player findPlayer = playerDao.findByLoginId(loginId);

        if(findPlayer == null) {
            System.out.println("존재하지 않는 유저입니다.");

        } else {
            if(findPlayer.getPassword().equals(password)) {
                this.loginId = loginId;
            } else {
                System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
            }
        }
        System.out.println(findPlayer.getNickname()+ "님 환영합니다.");
    }

    public void logout() {
        this.loginId = null;
    }

    public void updateNickname() {
    }

    public void updatePassword() {
    }



}
