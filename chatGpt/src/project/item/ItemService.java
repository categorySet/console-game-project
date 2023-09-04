package project.item;

import project.config.UIController;

import java.util.ArrayList;
import java.util.Scanner;

public class ItemService {
    private ItemDao itemDao;
    private UIController uiController;

    public ItemService() {
        itemDao = new ItemDao();
        uiController = new UIController();
    }

    public void addItem(Scanner sc) {
        // Manager 권한 확인
        uiController.printSubTitle("아이템 등록");
        System.out.print("아이템 이름 : ");
        String itemName = sc.next();
        System.out.print("게임 번호 (0.기본 1.마피아) : ");        //game_id와 game_name을 조회하여 자동으로 작성해야할듯함 (우선순위 낮음 시간이 된다면 진행)
        int gameId = sc.nextInt();
        System.out.print("카테고리 (칭호, 스킨, 둘다, 마피아, 의사, 경찰) : ");
        String category = sc.next();
        System.out.print("가격 : ");
        int price = sc.nextInt();
        System.out.print("limitedEdition(T/F) : ");
        String s = sc.next();
        boolean limitedEdition = false;
        int amount = 0;
        if (s.toUpperCase().equals("T")) {
            limitedEdition = true;
            System.out.print("수량 : ");
            amount = sc.nextInt();
        } else if (s.toUpperCase().equals("F")) {
            amount = 999999999;
        }
        System.out.print("아이템 정보 : ");
        sc.nextLine(); // 입력 버퍼를 비워주는 역할
        String itemInfo = sc.nextLine();
        itemDao.insert(new Item(itemName, gameId, category, price, limitedEdition, amount, itemInfo));
        // TODO: manager 기능 중 item 등록 삭제 -> 데이터 init에 추가
    }

    // 번호로 검색
    public void getItem(Scanner sc) {
        uiController.printSubTitle("아이템 검색");
        System.out.print("아이템 번호 :");
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
        uiController.printSubTitle("상점 별 검색");
        System.out.print("게임 번호 (0.기본 1.마피아 2.퀴즈) : ");
        int gameId = sc.nextInt();
        ArrayList<Item> list = itemDao.selectByGameId(gameId);
        for (Item i : list) {
            System.out.println(i);
        }
    }

    // 전체 검색
    public void printAll() {
        uiController.printSubTitle("아이템 전체 검색");
        ArrayList<Item> list = getAll();
        printAllItems(list);
    }


    public void printAllItems(ArrayList<Item> items) {
        System.out.printf("%-10s%-15s%-10s%-10s%-15s%n", "번호", "이름", "가격", "게임", "정보");

        if (items.isEmpty()) {
            System.out.println("추후 업데이트 예정입니다.");
        }
        for (Item item : items) {
            String category = null;
            if(item.getGameId() ==  0) {
                category = "전체";
            } else if(item.getGameId() == 1) {
                category = "마피아 게임";
            }


            System.out.printf("%-10s%-15s%-10s%-10s%-15s%n", item.getItemId(), item.getItemName(), item.getPrice(), category, item.getItemInfo());
        }
    }

    public ArrayList<Item> getAll() {
        return itemDao.selectAll();
    }

    // 아이템 수정
    public void editItem(Scanner sc) {
        uiController.printSubTitle("아이템 수정");
        System.out.print("아이템 번호 :");
        int itemId = sc.nextInt();
        Item i = itemDao.select(itemId);
        if (i == null) {
            System.out.println("해당 아이템 번호가 존재하지 않습니다.");
        } else {
            uiController.printSubTitle("수정 전");
            System.out.println(i);
            System.out.print("새로운 가격 : ");
            int price = sc.nextInt();
            System.out.print("limitedEdition(T/F) : ");
            String s = sc.next();
            boolean limitedEdition = false;
            int amount = 0;
            if (s.equalsIgnoreCase("T")) {
                limitedEdition = true;
                System.out.print("판매 수량 : ");
                amount = sc.nextInt();
            } else if (s.equalsIgnoreCase("F")) {
                amount = 999999999;
            }
            System.out.print("새로운 아이템 정보 : ");
            sc.nextLine();
            String info = sc.nextLine();
            i.updateItem(price, limitedEdition, amount, info);
            itemDao.update(i);
        }
    }

    // 아이템 삭제
    public void deleteItem(Scanner sc) {
        uiController.printSubTitle("아이템 삭제");

        System.out.print("아이템 번호 : ");
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