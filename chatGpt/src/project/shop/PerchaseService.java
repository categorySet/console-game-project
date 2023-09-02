package project.shop;

import project.item.BasicItem;
import project.item.ColorCode;
import project.item.Item;
import project.item.ItemDao;
import project.player.Player;
import project.player.PlayerDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static project.player.PlayerService.loginId;
import static project.player.PlayerService.nickname;


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
            Player player = playerDao.findByLoginId(loginId); // 현재 플레이어

            if (player.getCredit() >= price) {
                purchaseDao.insert(new Purchase(itemId, player.getPlayerId()));
                playerDao.updateCredit(player, -price); // player의 credit으로 아이템 구매
                if (selectedItem.isLimitedEdition()) {
                    if (selectedItem.getAmount() > 0) {
                        selectedItem.decrementAmount();
                    } else {
                        System.out.println("아이템의 수량이 없어 구매할 수 없습니다.");
                    }
                }
                itemDao.update(selectedItem); // limited edition인 아이템은 구매하면 amount 값이 감소
                System.out.printf("%s이(가) 구매되었습니다. 잔액: %d%n", selectedItem.getItemName(), player.getCredit());
                applyItemToNickname(selectedItem);
            } else {
                System.out.printf("credit이 부족합니다. 잔액: %d%n", player.getCredit());
            }
        }
    }

    public List<Integer> printPurchaseById() {
        System.out.println("======= 구매 내역 =======");
        List<Integer> result = new ArrayList<>();

        purchaseDao.selectByPlayerId(playerDao.findByLoginId(loginId).getPlayerId());

        ArrayList<Purchase> purchases = purchaseDao.selectByPlayerId(playerDao.findByLoginId(loginId).getPlayerId());
        for (Purchase p : purchases) {
            System.out.printf("주문번호: %d / 아이템 이름: %s / 주문날짜: %s%n", p.getPurchaseId(), itemDao.select(p.getItemId()).getItemName(), p.getCreateDate());
            result.add(p.getItemId());
        }
        return result;
    }

    public void applyItemToNickname(Item item) {
        if(item == null) {
            System.out.println("존재하지 않는 아이템 입니다.");
        } else {
            if (item.getCategory().equals(BasicItem.title.getContents())) {
                nickname = item.getItemName() + " " + nickname; // 닉네임 앞에 칭호를 붙임
                System.out.println("nickname: " + nickname);
                System.out.println("칭호가 적용되었습니다.");

            } else if (item.getCategory().equals(BasicItem.color.getContents())) {
                for (ColorCode c : ColorCode.values()) {
                    if (item.getItemName().equalsIgnoreCase(c.name())) {
                        String colorCode = c.getCode();
                        nickname = colorCode + " " + nickname + " " + ColorCode.RESET.getCode(); // 닉네임에 색상 코드를 적용
                    }
                }
                System.out.println("nickname: " + nickname);
                System.out.println("스킨이 적용되었습니다.");

            } else if (item.getCategory().equals(BasicItem.edition.getContents())) {
                // TODO: 칭호와 스킨 두 가지를 모두 적용하는 로직
            }
        }
    }
}
