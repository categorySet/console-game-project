package project.player;

import java.sql.Date;

public class Player {
    private int playerId;
    private String loginId;
    private String nickname;
    private int credit;
    private Date createDate;
    private Date lastModifiedDate;
    
    public Player() {}

	public Player(int playerId, String loginId, String nickname, int credit, Date createDate, Date lastModifiedDate) {
		this.playerId = playerId;
		this.loginId = loginId;
		this.nickname = nickname;
		this.credit = credit;
		this.createDate = createDate;
		this.lastModifiedDate = lastModifiedDate;
	}

	public int getPlayerId() {
		return playerId;
	}


	public String getLoginId() {
		return loginId;
	}


	public String getNickname() {
		return nickname;
	}


	public int getCredit() {
		return credit;
	}

	public Date getCreateDate() {
		return createDate;
	}


	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}


	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", loginId=" + loginId + ", nickname=" + nickname + ", credit=" + credit
				+ ", createDate=" + createDate + ", lastModifiedDate=" + lastModifiedDate + "]";
	}
    
    
}
