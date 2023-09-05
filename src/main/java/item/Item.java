package item;

import java.sql.Date;
import java.time.LocalDate;

public class Item {
    private int itemId;
    private String itemName;
    private int gameId;
    private int price;
    private boolean limitedEdition;
    private int amount;
    private String itemInfo;
    private String category;
    private Date createDate;
    private Date lastModifiedDate;


    public Item() {
    }

    public Item(int itemId, String itemName, int gameId, String category, int price, boolean limitedEdition, int amount) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.gameId = gameId;
        this.category = category;
        this.price = price;
        this.limitedEdition = limitedEdition;
        this.amount = amount;
    }

    public Item(String itemName, int gameId, String category, int price, boolean limitedEdition, int amount, String itemInfo) {
        this.itemName = itemName;
        this.gameId = gameId;
        this.category = category;
        this.price = price;
        this.limitedEdition = limitedEdition;
        this.amount = amount;
        this.itemInfo = itemInfo;
        this.createDate = Date.valueOf(LocalDate.now());
        this.lastModifiedDate = Date.valueOf(LocalDate.now());
    }
    public Item(int itemId, String itemName, int gameId, String category, int price, boolean limitedEdition, int amount, String itemInfo, Date createDate, Date lastModifiedDate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.gameId = gameId;
        this.category = category;
        this.price = price;
        this.limitedEdition = limitedEdition;
        this.amount = amount;
        this.itemInfo = itemInfo;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getGameId() {
        return gameId;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public boolean isLimitedEdition() {
        return limitedEdition;
    }

    public int getAmount() {
        return amount;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", gameId=" + gameId +
                ", price=" + price +
                ", limitedEdition=" + limitedEdition +
                ", amount=" + amount +
                ", itemInfo='" + itemInfo + '\'' +
                ", category='" + category + '\'' +
                ", createDate=" + createDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }

    public void updateItem(int newPrice, boolean newLimitedEdition, int newAmount, String newInfo) {
        this.price = newPrice;
        this.limitedEdition = newLimitedEdition;
        this.amount = newAmount;
        this.itemInfo = newInfo;
    }

    public void decrementAmount() {
        this.amount = --amount;
    }
}