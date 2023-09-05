package shop;

import java.sql.Date;
import java.time.LocalDate;

public class Purchase {
    private int purchaseId;
    private int itemId;
    private int playerId;
    private Date createDate;
    private Date lastModifiedDate;

    public Purchase() {
    }

    // order 객체 생성할 때
    public Purchase(int itemId, int playerId) {
        this.itemId = itemId;
        this.playerId = playerId;
        this.createDate = Date.valueOf(LocalDate.now());
        this.lastModifiedDate = Date.valueOf(LocalDate.now());
    }

    // 전체 값 조회
    public Purchase(int purchaseId, int itemId, int playerId, Date createDate, Date lastModifiedDate) {
        this.purchaseId = purchaseId;
        this.itemId = itemId;
        this.playerId = playerId;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getPurchaseId() {
        return purchaseId;
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
        return "purchase{" +
                "purchaseId=" + purchaseId +
                ", itemId=" + itemId +
                ", playerId=" + playerId +
                ", createDate=" + createDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}



