package project.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.config.DBConnect;
import project.player.Player;

public class AdminDao {
	private DBConnect dbconn;

	public AdminDao() {
		dbconn = DBConnect.getInstance();
	}

	// 플레이어 전체 조회
	public ArrayList<Player> selectAll() {
		ArrayList<Player> list = new ArrayList<Player>();

		Connection conn = dbconn.conn();

		String sql = "SELECT * FROM player";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5),
						rs.getDate(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbconn.disconnectDB(conn);
		}
		return list;
	}

	// 플레이어 검색
	public Player select(String nickname) {
		Connection conn = dbconn.conn();

		String sql = "SELECT * FROM player WHERE nickname = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, nickname);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5),
						rs.getDate(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.disconnectDB(conn);
		}
		return null;
	}

	// 크레딧 수정
	public void updateCredit(int playerId) {
		Connection conn = dbconn.conn();

		String sql = "UPDATE player SET credit=? WHERE player_id = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			Player p = new Player();

			pstmt.setInt(1, p.getCredit());
			pstmt.setInt(2, playerId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.disconnectDB(conn);
		}
	}
	
	public void update(Player p) {
		Connection conn = dbconn.conn();
		
		String sql = "UPDATE player SET playerId = ?, loginId = ?,nickname = ?, credit = ? WHERE player_id = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, p.getPlayerId());
			pstmt.setString(2, p.getLoginId());
			pstmt.setString(3, p.getNickname());
			pstmt.setInt(4, p.getCredit());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}dbconn.disconnectDB(conn);
	}

	//블랙리스트에 추가
	public void addBlackList(int playerId, BanReason reason) {
		Connection conn = dbconn.conn();
		
		String sql = "INSERT INTO blacklist VALUES(?,?,?,?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			BlackList b = new BlackList();
			
			pstmt.setInt(1, playerId);
			pstmt.setInt(2, reason.Ban); 
			pstmt.setDate(3, b.getCreateDate()); 
			pstmt.setDate(4, b.getLastModifiedDate()); 
			pstmt.executeUpdate();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbconn.disconnectDB(conn);
		}
	}
	
	//비밀번호
	public void insertPwd(String pwd) {
		Connection conn = dbconn.conn();
		
		String sql = "INSERT INTO admin(pwd) VALUES (?)";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pwd);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbconn.disconnectDB(conn);
		}
	}
	
	public void selectPwd(String pwd) {
		Connection conn = dbconn.conn();
		
		String sql = "SELECT FROM admin WHERE pwd=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pwd);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbconn.disconnectDB(conn);
		}
		
	}
}
