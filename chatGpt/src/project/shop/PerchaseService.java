package project.shop;

import project.item.Item;
import project.item.ItemDao;
import project.player.Player;
import project.player.PlayerDao;
import project.player.PlayerService;

import java.util.ArrayList;
import java.util.Scanner;


public class PerchaseService {
    private PurchaseDao purchaseDao;
    private PlayerDao playerDao;
    private ItemDao itemDao;

    public PerchaseService() {
        purchaseDao = new PurchaseDao();
        playerDao = new PlayerDao();
        itemDao = new ItemDao();
    }

    // 주문
    public void addPurchase(Scanner sc, ArrayList<Item> items) {
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
        if ( itemId < 0 || itemDao.select(itemId) == null) {
            System.out.println("해당하는 아이템이 없습니다.");
        } else {
            Item selectedItem = itemDao.select(itemId);
            int price = selectedItem.getPrice(); // 선택한 아이템의 가격
            Player player = playerDao.findByLoginId(PlayerService.loginId); // 현재 플레이어
            purchaseDao.insert(new Purchase(itemId, player.getPlayerId(), false));
            playerDao.updateCredit(player, player.getCredit() - price); // player의 credit으로 아이템 구매
            System.out.printf("%s이(가) 구매되었습니다. 잔액: %n", selectedItem.getItemName(), player.getCredit());
        }
    }
}
