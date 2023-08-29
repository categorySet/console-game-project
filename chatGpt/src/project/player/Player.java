package project.player;

import java.sql.Date;

public class Player {

    private int playerId;
    private String loginId;
    private String password;

    private String nickname;
    private int credit;
    private Date createDate;
    private Date lastModifiedDate;

    public Player(String loginId, String password, String nickname, int credit, Date createDate, Date lastModifiedDate) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.credit = credit;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Player(String loginId, String password, String nickname, int credit) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.credit = credit;
    }
}
