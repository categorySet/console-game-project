package project.admin;

public class BlackList {
    private int blackListId;
    private BanReason reason;
    
    public BlackList() {}
    
	public BlackList(int blackListId, BanReason reason) {
		this.blackListId = blackListId;
		this.reason = reason;
	}

	public int getBlackListId() {
		return blackListId;
	}

	public BanReason getReason() {
		return reason;
	}     
    
}
