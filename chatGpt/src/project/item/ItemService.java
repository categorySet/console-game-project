package project.item;

import java.util.ArrayList;
import java.util.Scanner;

public class ItemService {
    private ItemDao itemDao;

    public ItemService() {
        itemDao = new ItemDao();
    }

    public void addItem(Scanner sc) {
        // Admin 권한 확인
        System.out.println("=== 아이템 등록 ===");
        System.out.print("item name: ");
        String itemName = sc.next();
        System.out.print("game id (0.기본 1.마피아 2.퀴즈): ");        //game_id와 game_name을 조회하여 자동으로 작성해야할듯함 (우선순위 낮음 시간이 된다면 진행)
        int gameId = sc.nextInt();
        System.out.print("category (칭호, 스킨, 둘다, 마피아, 의사, 경찰): ");
        String category = sc.next();
        System.out.print("price: ");
        int price = sc.nextInt();
        System.out.print("limitedEdition(T/F): ");
        String s = sc.next();
        boolean limitedEdition = false;
        int amount = 0;
        if (s.toUpperCase().equals("T")) {
            limitedEdition = true;
            System.out.print("amount: ");
            amount = sc.nextInt();
        } else if (s.toUpperCase().equals("F")) {
            amount = 999999999;
        }
        System.out.print("item info: ");
        sc.nextLine(); // 입력 버퍼를 비워주는 역할
        String itemInfo = sc.nextLine();
        itemDao.insert(new Item(itemName, gameId, category, price, limitedEdition, amount, itemInfo));
        // TODO: admin 기능 중 item 등록 삭제 -> 데이터 init에 추가
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
            if (s.equalsIgnoreCase("T")) {
                limitedEdition = true;
                System.out.print("limited amount: ");
                amount = sc.nextInt();
            } else if (s.equalsIgnoreCase("F")) {
                amount = 999999999;
            }
            System.out.print("new Info: ");
            sc.nextLine();
            String info = sc.nextLine();
            i.updateItem(price, limitedEdition, amount, info);
            itemDao.update(i);
        }
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