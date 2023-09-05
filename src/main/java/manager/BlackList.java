package manager;

import java.sql.Date;

public class BlackList {
	private int blackListId;
	private int playerId;
	private String reason;
	private Date createDate;
	private Date lastModifiedDate;

	public BlackList() {}

	public BlackList(int blackListId, int playerId, String reason, Date createDate, Date lastModifiedDate) {
		this.blackListId = blackListId;
		this.playerId = playerId;
		this.reason = reason;
		this.createDate = createDate;
		this.lastModifiedDate = lastModifiedDate;
	}//ManagerDao의 selectAllBlackList()에서 사용

	public BlackList(int blackListId,  String reason, Date createDate, Date lastModifiedDate) {
		this.blackListId = blackListId;
		this.reason = reason;
		this.createDate = createDate;
		this.lastModifiedDate = lastModifiedDate;
	}//AdminDao의 findByPlayerId()에서 사용

	public int getBlackListId() {
		return blackListId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public String getReason() {
		return reason;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	@Override
	public String toString() {
		return "BlackList [blackListId=" + blackListId + ", playerId=" + playerId + ", reason=" + reason
				+ ", createDate=" + createDate + ", lastModifiedDate=" + lastModifiedDate + "]";
	}

}
