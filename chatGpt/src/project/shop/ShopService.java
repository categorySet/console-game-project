package project.shop;

import project.item.Item;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ShopService {
    private ShopDao shopDao;

    public ShopService() {
        shopDao = new ShopDao();
    }

    // 주문
    public void addShop(Scanner sc, ArrayList<Item> items) {
        System.out.println("======= 아이템 구매 =======");
        System.out.println("0.기본 상점 1.마피아 상점 2.퀴즈 상점");
        System.out.print("입력: ");
        int gameId = sc.nextInt();

        System.out.println("======= 아이템 목록 =======");
        for (Item item : items) {
            if (item.getGameId() == gameId) {
                System.out.println(item);
            }
        }

        System.out.print("구매할 아이템 번호: ");
        int itemId = sc.nextInt();
        if (itemId < 0 || itemId > items.size()) {
            System.out.println("해당하는 아이템이 없습니다.");
        } else {
            int price = items.get(itemId).getPrice(); // 선택한 아이템의 가격
            shopDao.insert(new Shop(0, itemId, 0, false, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now())));
            // TODO: player의 credit으로 아이템 구매
        }
    }
}
