package project.admin;

import project.item.Item;
import project.item.ItemDao;
import project.player.Player;

import java.awt.desktop.UserSessionEvent.Reason;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminService {
	private AdminDao adminDao;
	private ItemDao itemDao;

	public AdminService() {
		adminDao = new AdminDao();
		itemDao = new ItemDao();
	}

	public void addCredit(Scanner sc) {
		System.out.println("===크레딧 부여===");

		System.out.println("크레딧을 부여할 플레이어 닉네임:");
		String nickname = sc.next();
		Player p = adminDao.select(nickname);

		System.out.println("부여할 크레딧:");
		int newCredit = sc.nextInt();

		adminDao.updateCredit(p.getCredit() + newCredit);

		System.out.println("크레딧이 부여되었습니다.");

	}

	public void subCredit(Scanner sc) {
		System.out.println("===크레딧 삭감===");

		System.out.println("크레딧을 삭감할 플레이어 닉네임:");
		String nickname = sc.next();
		Player p = adminDao.select(nickname);

		System.out.println("삭감할 크레딧:");
		int newCredit = sc.nextInt();

		adminDao.updateCredit(p.getCredit() - newCredit);

		System.out.println("크레딧이 삭감되었습니다.");

	}

	public void printAll() {
		System.out.println("===모든 플레이어 조회===");
		ArrayList<Player> list = new ArrayList<>();

		for (Player p : list) {
			System.out.println(p);
		}
	}

	public void addToBlackList(Scanner sc) {	//TODO 해당 메서드는 메뉴로 이동하거나 playerService에서 구동.
		System.out.println("===플레이어 블랙리스트에 추가===");

		System.out.println("블랙리스트에 추가될 플레이어:");
		int playerId = sc.nextInt();
		
		System.out.println("밴 사유:");
		int index = sc.nextInt();
		adminDao.addBlackList(playerId, index);
		
		boolean flag = adminDao.checkBlackList(playerId);
		
		while(flag) {
				System.out.println("플레이어가 블랙리스트에 추가 되었습니다. 활동이 금지됩니다."); 
				}
		}

	public void printAllBlackList() {
		System.out.println("=== 블랙리스트 전체 출력===");
		ArrayList<BlackList> list = new ArrayList<>();

		for (BlackList b : list) {
			System.out.println(b);
		}
	}

	public void delFromBlackList(Scanner sc) {
		System.out.println("===블랙리스트에서 플레이어 삭제===");
		
		System.out.println("밴 해제할 플레이어:");
		int playerId = sc.nextInt();

		adminDao.delBlackList(playerId);
		
		System.out.println("블랙리스트에서 삭제되었습니다.");
	}

	public void addItem(Scanner sc) {
		System.out.println("=== 아이템 등록 ===");
		System.out.print("item name: ");
		String itemName = sc.next();
		System.out.print("game id (0.기본 1.마피아 2.퀴즈): "); // game_id와 game_name을 조회하여 자동으로 작성해야할듯함 (우선순위 낮음 시간이 된다면 진행)
		int gameId = sc.nextInt();
		System.out.print("price: ");
		int price = sc.nextInt();
		System.out.print("limitedEdition(T/F): ");
		String s = sc.next();
		boolean limitedEdition = false;
		int amount = 0;
		if (s.equals("T")) {
			limitedEdition = true;
			System.out.print("amount: ");
			amount = sc.nextInt();
		} else if (s.equals("F")) {
			amount = 999999999;
		}
		itemDao.insert(new Item(0, itemName, gameId, price, limitedEdition, amount));
	}

	// 번호로 검색
	public void getItem(Scanner sc) {
		System.out.println("=== 아이템 검색 ===");
		System.out.print("item id:");
		int itemId = sc.nextInt();
		Item i = itemDao.select(itemId);
		if (i == null) {
			System.out.println("해당 아이템 번호가 존재하지 않습니다.");
		} else {
			System.out.println(i);
		}
	}

	// gameId로 검색
	public void getByGameId(Scanner sc) {
		System.out.println("=== 상점별 검색 ===");
		System.out.print("game id (0.기본 1.마피아 2.퀴즈): ");
		int gameId = sc.nextInt();
		ArrayList<Item> list = itemDao.selectByGameId(gameId);
		for (Item i : list) {
			System.out.println(i);
		}
	}

	// 전체 검색
	public void printAllItem() {
		System.out.println("=== 전체 검색 ===");
		ArrayList<Item> list = getAll();
		for (Item i : list) {
			System.out.println(i);
		}
	}

	public ArrayList<Item> getAll() {
		return itemDao.selectAll();
	}

	// 아이템 수정
	public void editItem(Scanner sc) {
		System.out.println("=== 아이템 수정 ===");
		System.out.print("item id:");
		int itemId = sc.nextInt();
		Item i = itemDao.select(itemId);
		if (i == null) {
			System.out.println("해당 아이템 번호가 존재하지 않습니다.");
		} else {
			System.out.println("== 수정 전 ==");
			System.out.println(i);
			System.out.print("new price: ");
			int price = sc.nextInt();
			System.out.print("limitedEdition(T/F): ");
			String s = sc.next();
			boolean limitedEdition = false;
			int amount = 0;
			if (s.equals("T")) {
				limitedEdition = true;
				System.out.print("limited amount: ");
				amount = sc.nextInt();
			} else if (s.equals("F")) {
				amount = 999999999;
			}
			i.updateItem(price, limitedEdition, amount);
			itemDao.update(i);
	}

	// 아이템 삭제
	public void deleteItem(Scanner sc) {
		System.out.println("=== 아이템 삭제 ===");
		System.out.print("item id: ");
		int itemId = sc.nextInt();
		sc.nextLine();
		Item i = itemDao.select(itemId);
		if (i == null) {
			System.out.println("해당 아이템 번호가 존재하지 않습니다.");
		} else {
			try {
				itemDao.delete(itemId);
				System.out.println("아이템이 삭제되었습니다.");
			} catch (Exception e) {
				System.out.println("아이템 삭제 중 오류가 발생했습니다: " + e.getMessage());
			}
		}
	}

}
