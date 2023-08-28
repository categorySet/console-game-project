package project.admin;

public class Admin {
    private int adminId;
    private int playerId;
    private int blackListId;

    public Admin() {
    }

    public Admin(int adminId, int playerId, int blackListId) {
//        super();                          //FIXME : super?
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

    public int getBlackListId() {
        return blackListId;
    }

    @Override
    public String toString() {
        return "Admin [adminId=" + adminId + ", playerId=" + playerId + ", blackListId=" + blackListId + "]";
    }


}
