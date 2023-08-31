package project.shop;

import java.sql.Date;
import java.time.LocalDate;

public class Order {
    private int orderId;
    private int itemId;
    private int playerId;
    private Date createDate;
    private Date lastModifiedDate;

    public Order() {
    }

    // order 객체 생성할 때
    public Order(int itemId, int playerId, boolean limitedEdition) {
        this.itemId = itemId;
        this.playerId = playerId;
        this.createDate = Date.valueOf(LocalDate.now());
        this.lastModifiedDate = Date.valueOf(LocalDate.now());
    }

    // 전체 값 조회
    public Order(int orderId, int itemId, int playerId, boolean limitedEdition, Date createDate, Date lastModifiedDate) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.playerId = playerId;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getOrderId() {
        return orderId;
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
        return "Order{" +
                "orderId=" + orderId +
                ", itemId=" + itemId +
                ", playerId=" + playerId +
                ", createDate=" + createDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}



