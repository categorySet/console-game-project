package project.player;

import java.sql.Date;
import java.time.LocalDate;

public class Player {

    private int playerId;
    private String loginId;
    private String password;
	private String confirmPassword;

    private String nickname;
    private int credit;
    private Date createDate;
    private Date lastModifiedDate;
    
    public Player() {}

	//Sign up
	public Player(String loginId, String password, String confirmPassword, String nickname) {
		this.loginId = loginId;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.nickname = nickname;
	}

	//Change Nickname
	public Player(int playerId, String nickname) {
		this.playerId = playerId;
		this.nickname = nickname;
		this.lastModifiedDate = Date.valueOf(LocalDate.now());
	}

	//Change Password
	public Player(int playerId, String password, String confirmPassword) {
		this.playerId = playerId;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.lastModifiedDate = Date.valueOf(LocalDate.now());
	}

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

	public Player(int playerId, String loginId, String password, String nickname, int credit, Date createDate, Date lastModifiedDate) {
		this.playerId = playerId;
		this.loginId = loginId;
		this.password = password;
		this.nickname = nickname;
		this.credit = credit;
		this.createDate = createDate;
		this.lastModifiedDate = lastModifiedDate;
	}

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
	public String getLoginId() {
		return loginId;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
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
