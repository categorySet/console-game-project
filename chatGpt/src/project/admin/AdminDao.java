package project.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.config.db.DBConnect;
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

		String sql = "SELECT * FROM player";// playerId, loginId, password, credit, createDate, lastModifiedDate

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5),
						rs.getDate(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.disconnectDB(conn);
		}
		return list;
	}

	// 플레이어 검색
	public Player select(String nickname) {
		Connection conn = dbconn.conn();

		String sql = "SELECT * FROM player WHERE nickname = ?";// playerId, loginId, password, credit, createDate,
																// lastModifiedDate

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

	// 플레이어가 블랙리스트에 올랐는지
	public boolean checkBlackList(int playerId) {
		Connection conn = dbconn.conn();

		String sql = "SELECT * FROM blacklist WHERE player_Id=?";// blackListId, playerId, reason, createDate, lastModifiedDate

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, playerId);
			ResultSet rs = pstmt.executeQuery();
			
			return true;
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

		String sql = "SELECT * FROM blacklist";// blackListId, playerId, reason, createDate, lastModifiedDate

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
	public void addBlackList(int playerId, int index) {
		Connection conn = dbconn.conn();

		String sql = "INSERT INTO blacklist VALUES(?,?,?,?)"; // player_id, reason, c_date, m_date

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			BlackList b = new BlackList();

			pstmt.setInt(1, playerId);
			pstmt.setInt(2, index);
			pstmt.setDate(3, b.getCreateDate());
			pstmt.setDate(4, b.getLastModifiedDate());
			pstmt.executeUpdate();

			System.out.println("해당 플레이어가 블랙리스트에 추가되었습니다.");

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

			System.out.println("해당 플레이어가 블랙리스트에서 해제되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.disconnectDB(conn);
		}
	}
}
