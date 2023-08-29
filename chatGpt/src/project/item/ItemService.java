package project.item;

import java.util.ArrayList;
import java.util.Scanner;

public class ItemService {
    private ItemDao itemDao;

    public ItemService() {
        itemDao = new ItemDao();
    }

    // TODO: Admin만 아이템 추가, 수정, 삭제 가능
    public void addItem(Scanner sc) {
        // Admin 권한 확인
        System.out.println("=== 아이템 등록 ===");
        System.out.print("item name: ");
        String itemName = sc.next();
        System.out.print("game id (0.기본 1.마피아 2.퀴즈): ");
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
    public void printAll() {
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
        } // TODO: 바인딩 되지 않음. setter 없이 수정하려면?
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
    } // TODO: 삭제되지 않고 멈춰버림
}

