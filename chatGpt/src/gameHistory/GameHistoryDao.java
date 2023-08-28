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

        //FIXME : seq.~~.nextval 해야할까요?? (오라클 잘 몰라서), c_date m_date 추가 필요할듯 합니다.
        String sql = "INSERT INTO game_history VALUES (seq_game_history, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, gameId);
            pstmt.setInt(2, playerId);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //FIXME : connection close plz
            dbConn.disconnectDB(conn);
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

    public List<GameHistoryQueryVo> findByPlayerId(int playerId) {
        Connection conn = dbConn.conn();
        List<GameHistoryQueryVo> result = new ArrayList<>();

        String sql =
                "SELECT h.history_id AS historyId, g.game_name AS gameName, p.nickname AS nickname" +
                        " FROM game_history h" +
                        "    INNER JOIN game g" +
                        "        ON h.game_id = g.game_id" +
                        "    INNER JOIN player p" +
                        "        ON h.winner = p.player_id" +
                        " WHERE p.player_id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, playerId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result.add(new GameHistoryQueryVo(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }



}
