package manager;

import config.db.DBConnect;
import player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerDao {
	private DBConnect dbconn;

	public ManagerDao() {
		dbconn = DBConnect.getInstance();
	}

	// 크레딧 수정
	public void updateCredit(String nickname) {
		Connection conn = dbconn.conn();
		String sql = "UPDATE player SET credit=? WHERE nickname = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			Player p = new Player();

			pstmt.setInt(1, p.getCredit());
			pstmt.setString(2, p.getNickname());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.disconnectDB(conn);
		}
	}

	// 플레이어가 블랙리스트에 올랐는지
	public boolean checkBlackList(int playerId) {
		Connection conn = dbconn.conn();
		String sql = "SELECT black_list_id, reason, create_date, last_modified_date FROM blacklist WHERE player_id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, playerId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.disconnectDB(conn);
		}
		return false;
	}

	// 블랙리스트 전체 출력
	public ArrayList<BlackList> selectAllBlackList() {
		Connection conn = dbconn.conn();

		ArrayList<BlackList> list = new ArrayList<>();

		String sql = "SELECT black_list_id, player_id, reason, create_date, last_modified_date FROM blacklist";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				list.add(new BlackList(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getDate(5)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.disconnectDB(conn);
		}
		return list;
	}

	// 블랙리스트에 추가
	public void addBlackList(int playerId, String reason) {
		Connection conn = dbconn.conn();

		String sql = "INSERT INTO blacklist VALUES(seq_blacklist.nextval,?,?,?,?)";//player_id, reason, create_date, last_modified_date

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			BlackList b = new BlackList();

			pstmt.setInt(1, playerId);
			pstmt.setString(2, reason);
			pstmt.setDate(3, b.getCreateDate());
			pstmt.setDate(4, b.getLastModifiedDate());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.disconnectDB(conn);
		}
	}

	// 블랙리스트에서 삭제
	public void delBlackList(int playerId) {
		Connection conn = dbconn.conn();

		String sql = "DELETE blacklist WHERE player_id=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, playerId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.disconnectDB(conn);
		}
	}
}