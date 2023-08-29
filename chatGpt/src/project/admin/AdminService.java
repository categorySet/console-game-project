package project.admin;

import project.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminService {
	private AdminDao adminDao;
	public static String pwd;

	/**
	 * TODO : pwd는 강사님이 처리한 loginId와 같이 꼭 서비스에서 처리 안해도 될 수 있습니다. DB에 pwd를 저장하고 콘솔에
	 * 입력하는 것 만으로도 구현 가능할 듯 합니다.
	 */

	public AdminService() {
		adminDao = new AdminDao();
	}

	public void addCredit(Scanner sc) {
		System.out.println("===크레딧 부여===");

		System.out.println("크레딧을 부여할 플레이어:");
		String nickname = sc.next();
		Player p = adminDao.select(nickname);

		System.out.println("부여할 크레딧:");
		int newCredit = sc.nextInt();

		adminDao.updateCredit(p.getCredit() + newCredit);

	}

	public void subCredit(Scanner sc) {
		System.out.println("===크레딧 삭감===");

		System.out.println("크레딧을 삭감할 플레이어:");
		String nickname = sc.next();
		Player p = adminDao.select(nickname);

		System.out.println("삭감할 크레딧:");
		int newCredit = sc.nextInt();

		adminDao.updateCredit(p.getCredit() - newCredit);

	}

	public void printAll(ArrayList<Player> list) {
		System.out.println("모든 플레이어 조회");

		for (Player p : list) {
			System.out.println(p);
		}

	}

	public void editPlayer(Scanner sc) {
		System.out.println("플레이어 수정");

		System.out.print("수정할 플레이어:"); 
		String nickname = sc.next();
		Player p = adminDao.select(nickname);

		// 수정할 플레이어 내용

	}

	public void addToBlackList(Player player, BanReason reason) {

//		list.add(new Player());
	}

	public boolean isPlayerBlackListed(Player player) {

		}
		return false;
	}

	public BanReason getBanReason() {

		return null;
	}

}
