package project.admin;

import java.util.ArrayList;

public class Admin {
    private int adminId;
    private int playerId;
    private ArrayList<BlackList> blackListId;

    public Admin() {
    	ArrayList<BlackList> list = new ArrayList<>(); 
    }
    

	public Admin(int adminId, int playerId, ArrayList<BlackList> blackListId) {
		this.adminId = adminId;
		this.playerId = playerId;
		this.blackListId = blackListId;
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

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", playerId=" + playerId + ", blackListId=" + blackListId + "]";
	}

}
