package project.shop;

import java.sql.Date;
import java.time.LocalDate;

public class Shop {
    private int shopId;
    private int itemId;
    private int playerId;
    private boolean limitedEdition;
    private Date createDate;
    private Date lastModifiedDate;

    public Shop() {
    }


    /**
     * 제가 생각하기에는 Shop 객체를 생성할 때는 createDate와 modifiedDate는
     * Param으로 받을 필요가 없기에 생성자 파라미터에서 제거하겠습니다.
     * Date.valueOf(LocalDate.now())를 사용하였기 때문에 자동으로 들어가는 값이므로
     */
    public Shop(int shopId, int itemId, int playerId, boolean limitedEdition) {
        this.shopId = shopId;
        this.itemId = itemId;
        this.playerId = playerId;
        this.limitedEdition = limitedEdition;
        this.createDate = Date.valueOf(LocalDate.now());
        this.lastModifiedDate = Date.valueOf(LocalDate.now()); // TODO: modifiedDate 수정
    }
    /** 대신 전체 값을 조회할 때 사용할 새로운 생성자를 만들었습니다.
     * select * from shop을 통해 Param으로 받은 Date를 this.date = date로 적용 */
    public Shop(int shopId, int itemId, int playerId, boolean limitedEdition, Date createDate, Date lastModifiedDate) {
        this.shopId = shopId;
        this.itemId = itemId;
        this.playerId = playerId;
        this.limitedEdition = limitedEdition;
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
        return "Shop{" +
                "shopId=" + shopId +
                ", itemId=" + itemId +
                ", playerId=" + playerId +
                ", limitedEdition=" + limitedEdition +
                ", createDate=" + createDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}



