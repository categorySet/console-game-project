package project.admin;

import project.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminService {
    private AdminDao adminDao;           //dao 이름은 adminDao로 변경하겠습니다.
    public static String pwd;           //비밀번호 어떻게 설정할지
    /** TODO : pwd는 강사님이 처리한 loginId와 같이 꼭 서비스에서 처리 안해도 될 수 있습니다.
     * DB에 pwd를 저장하고 콘솔에 입력하는 것 만으로도 구현 가능할 듯 합니다.
     * */

    public AdminService() {
        adminDao = new AdminDao();
    }

    public void addCredit(Scanner sc) {
        System.out.println("===크레딧 부여===");

        System.out.println("크레딧을 부여할 플레이어:");
        String playerName = sc.next();

        System.out.println("부여할 크레딧:");
        int credit = sc.nextInt();

//        Player p = adminDao.update(new Player());     //오류 코드로 일단 주석처리 했습니다.

    }

    public void subCredit(Scanner sc) {
        System.out.println("===크레딧 삭감===");

        System.out.println("크레딧을 삭감할 플레이어 아이디:");
        int playerId = sc.nextInt();

        System.out.println("삭감할 크레딧:");
        int credit = sc.nextInt();
    }

    public void printAll(ArrayList<Player> list) {
        System.out.println("모든 플레이어 조회");

        for (Player p : list) {
            System.out.println(p);//toString으로 변환 필요
        }

    }

    public void editPlayer(Scanner sc) {
        System.out.println("플레이어 수정");

        System.out.print("수정할 플레이어:");
        int playerId = sc.nextInt();
        Player p = adminDao.select(playerId);

        //수정할 플레이어 내용

    }






}
