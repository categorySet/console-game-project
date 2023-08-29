package project.shop;

import java.sql.Date;
import java.time.LocalDate;

public class Shop {
    private int shopId;
    private int itemId;
    private int playerId;
    private Date createDate;
    private Date lastModifiedDate;

    public Shop() {
    }

    // shop 객체 생성할 때
    public Shop(int shopId, int itemId, int playerId, boolean limitedEdition) {
        this.shopId = shopId;
        this.itemId = itemId;
        this.playerId = playerId;
        this.createDate = Date.valueOf(LocalDate.now());
        this.lastModifiedDate = Date.valueOf(LocalDate.now()); // TODO: modifiedDate 수정
    }

    // 전체 값 조회
    public Shop(int shopId, int itemId, int playerId, boolean limitedEdition, Date createDate, Date lastModifiedDate) {
        this.shopId = shopId;
        this.itemId = itemId;
        this.playerId = playerId;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getShopId() {
        return shopId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", itemId=" + itemId +
                ", playerId=" + playerId +
                ", createDate=" + createDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}



