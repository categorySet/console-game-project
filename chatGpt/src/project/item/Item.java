package project.item;

import java.sql.Date;
import java.time.LocalDate;

public class Item {
    private int itemId;
    private String itemName;
    private int gameId;
    private int price;
    private int amount;
    private boolean limitedEdition;
    private Date createDate;
    private Date lastModifiedDate;

    public Item() {
    }

    public Item(int itemId, String itemName, int gameId, int price, int amount, boolean limitedEdition) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.gameId = gameId;
        this.price = price;
        this.amount = amount;
        this.limitedEdition = limitedEdition;
        this.createDate = Date.valueOf(LocalDate.now());
        this.lastModifiedDate = Date.valueOf(LocalDate.now());
    }

    public Item(int itemId, String itemName, int gameId, int price, int amount, boolean limitedEdition, Date createDate, Date lastModifiedDate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.gameId = gameId;
        this.price = price;
        this.amount = amount;
        this.limitedEdition = limitedEdition;
        this.createDate = Date.valueOf(LocalDate.now());
        this.lastModifiedDate = Date.valueOf(LocalDate.now());
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

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isLimitedEdition() {
        return limitedEdition;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", limitedEdition=" + limitedEdition +
                '}';
    }
}
