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

		String sql = "select * from player";

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

	//플레이어 정보 조회
	public Player select(String nickname) {
		Connection conn = dbconn.conn();

		String sql = "select * from player where nickname = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, nickname);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
                return new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getDate(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbconn.disconnectDB(conn);
		}
		return null; 
	}

	// 수정
	public void updateCredit(int playerId) {
		Connection conn = dbconn.conn();

		String sql = "update player set credit=? where player_id = ?";

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
}
