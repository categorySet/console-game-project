package manager;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Manager {
    private int managerId;		//Manager PK
    private int playerId;		//Manager User의 ID (FK)
    private int pin;			//Manager 접속 Pin 번호
    private ArrayList<BlackList> blackListId;
    private Date createDate;
    private Date lastModifiedDate;

    public Manager() {
    	ArrayList<BlackList> list = new ArrayList<>();
    }

	public Manager(int managerId, int playerId, int pin, ArrayList<BlackList> blackListId) {
		this.managerId = managerId;
		this.playerId = playerId;
		this.pin = pin;
		this.blackListId = blackListId;
		this.createDate = Date.valueOf(LocalDate.now());
		this.lastModifiedDate = Date.valueOf(LocalDate.now());
	}

	public int getManagerId() {
		return managerId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public int getPin() {
		return pin;
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
		return "Manager [managerId=" + managerId + ", playerId=" + playerId + ", pin=" + pin + ", blackListId=" + blackListId
				+ ", createDate=" + createDate + ", lastModifiedDate=" + lastModifiedDate + "]";
	}
	
	
}

