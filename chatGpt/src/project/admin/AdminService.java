package project.admin;

import project.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminService {
	private AdminDao adminDao;

	public AdminService() {
		adminDao = new AdminDao();
	}
	
	public void loginAdmin(Scanner sc) {
		System.out.println("===관리자 로그인===");
		
		System.out.println("관리자 비밀번호:");
		String pwd = sc.next();

				
//		if(pwd.equals.selectPwd()) {
//		if(pwd.equals(selectPwd())) {//TODO 관리자 비밀번호 비교
//			System.out.println("비밀번호가 일치하지 않습니다.");
//		}else {
//			System.out.println("관리자로 로그인합니다.");
//		}
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
		System.out.println("===모든 플레이어 조회===");

		for (Player p : list) {
			System.out.println(p);
		}

	}

	public void editPlayer(Scanner sc) {
		System.out.println("===플레이어 수정===");

		System.out.print("수정할 플레이어 ID:");
		String playerId = sc.next();
		Player p = adminDao.select(playerId);

		if (p == null) {
			System.out.println(p + "는 존재하지 않는 플레이어입니다.");
		} else {
			System.out.print("변경할 닉네임:");
			String newNickname = sc.next();
		
//			adminDao.update(new Player());
		}
	}

	public void addToBlackList(Player player, BanReason reason) {
		adminDao.addBlackList(player.getPlayerId(), reason);
	}

	public boolean isPlayerBlackListed(Scanner sc) {
		System.out.println("=== 블랙리스트 확인 ===");
		System.out.println("player id: ");
		int playerId = sc.nextInt();
		
		
		return false;
	}
	
	public void banPlayer() {
		//TODO 블랙리스트에 오른 플레이어 밴하는 방법
	}

}
