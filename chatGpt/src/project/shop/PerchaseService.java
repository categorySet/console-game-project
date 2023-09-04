package project.shop;

import project.config.UIController;
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
    private UIController uiController;

    public PerchaseService() {
        purchaseDao = new PurchaseDao();
        playerDao = new PlayerDao();
        itemDao = new ItemDao();
        uiController = new UIController();
    }

    // 주문
    public void addPurchase(Scanner sc, ArrayList<Item> items) {
        uiController.printSubTitle("아이템 구매");
        System.out.println("0.기본 상점 1.마피아 상점 2.퀴즈 상점");
        int gameId = uiController.printInput(sc);

        uiController.printSubTitle("아이템 목록");
        printAllItems(new ArrayList<>(items.stream().filter(item -> item.getGameId() == gameId).toList()));  //선택된 게임 항목의 아이템만 ArrayList로 변환하여 화면에 출력

        int itemId = uiController.printInput(sc);
        if ( itemId < 0 || itemDao.select(itemId) == null) {
            System.out.println("해당하는 아이템이 없습니다.");
        } else {
            Item selectedItem = itemDao.select(itemId);
            int price = selectedItem.getPrice();                 // 선택한 아이템의 가격
            Player player = playerDao.findByLoginId(loginId);    // 현재 플레이어

            if (player.getCredit() >= price) {
                purchaseDao.insert(new Purchase(itemId, player.getPlayerId()));
                playerDao.updateCredit(player, -price);         // player의 credit으로 아이템 구매
                if (selectedItem.isLimitedEdition()) {
                    if (selectedItem.getAmount() > 0) {
                        selectedItem.decrementAmount();
                    } else {
                        System.out.println("아이템의 수량이 없어 구매할 수 없습니다.");
                    }
                }
                itemDao.update(selectedItem);                   // limited edition인 아이템은 구매하면 amount 값이 감소
                System.out.printf("%s이(가) 구매되었습니다. 잔액: %d%n", selectedItem.getItemName(), player.getCredit());
                applyItemToNickname(selectedItem);
            } else {
                System.out.printf("credit이 부족합니다. 잔액: %d%n", player.getCredit());
            }
        }
    }

    public List<Item> printPurchaseById() {
        uiController.printSubTitle("구매 내역");
        List<Item> result = new ArrayList<>();

        ArrayList<Purchase> purchases = purchaseDao.selectByPlayerId(playerDao.findByLoginId(loginId).getPlayerId());
        for (Purchase p : purchases) {
            Item findItem = itemDao.select(p.getItemId());
            System.out.printf("아이템 번호 : %d / 아이템 이름: %s / 구매일: %s%n", findItem.getItemId(), findItem.getItemName(), p.getCreateDate());

            result.add(itemDao.select(p.getItemId()));
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
                    if (item.getItemName().equalsIgnoreCase(c.getColorName())) {
                        String colorCode = c.getCode();
                        nickname = colorCode + " " + nickname + " " + ColorCode.RESET.getCode(); // 닉네임에 색상 코드를 적용
                        //TODO colorCode를 서버에 전송하는 방법
                        //1. 배열로 보낸다 nickname[0] = name nickname[1] =

                    }
                }
                System.out.println("nickname: " + nickname);
                System.out.println("스킨이 적용되었습니다.");

            } else if (item.getCategory().equals(BasicItem.edition.getContents())) {
                // TODO: 칭호와 스킨 두 가지를 모두 적용하는 로직


            }
        }
    }

    public void printAllItems(ArrayList<Item> items) {       //TODO :각 서비스의 출력들을 어디로 가져갈지 상의 Service, Controller
        System.out.printf("%-10s%-15s%-10s%-15s%n", "번호", "이름", "가격", "정보");
        for (Item item : items) {
            System.out.printf("%-10s%-15s%-10s%-15s%n", item.getItemId(), item.getItemName(), item.getPrice(), item.getItemInfo());
        }
    }
}
