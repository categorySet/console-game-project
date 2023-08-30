package project.admin;

import java.sql.Date;
import java.util.ArrayList;

public class Admin {
    private int adminId;
    private int playerId;
    private ArrayList<BlackList> blackListId; //TODO 테이블에 어떤 방식으로 넣어야 할까요?
    private Date createDate;
    private Date lastModifiedDate;

    public Admin() {
    	ArrayList<BlackList> list = new ArrayList<>(); 
    }

	public Admin(int adminId, int playerId, ArrayList<BlackList> blackListId, Date createDate, Date lastModifiedDate) {
		this.adminId = adminId;
		this.playerId = playerId;
		this.blackListId = blackListId;
		this.createDate = createDate;
		this.lastModifiedDate = lastModifiedDate;
	}

	public int getAdminId() {
		return adminId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public ArrayList<BlackList> getBlackListId() {
		return blackListId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", playerId=" + playerId + ", blackListId=" + blackListId + ", createDate="
				+ createDate + ", lastModifiedDate=" + lastModifiedDate + "]";
	}

}

