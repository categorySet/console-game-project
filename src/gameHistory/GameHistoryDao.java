package gameHistory;

import project.config.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameHistoryDao {

    private DBConnect dbConn;

    public GameHistoryDao() {
        this.dbConn = DBConnect.getInstance();
    }

    public int insert(int gameId, int playerId) {
        Connection conn = dbConn.conn();

        String sql = "INSERT INTO game_history VALUES (seq_game_history, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, gameId);
            pstmt.setInt(2, playerId);
            
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public List<GameHistory> findByGameId(int gameId) {
        Connection conn = dbConn.conn();

        List<GameHistory> result = new ArrayList<>();

        String sql = "SELECT * FROM game_history WHERE game_id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result.add(new GameHistory(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
