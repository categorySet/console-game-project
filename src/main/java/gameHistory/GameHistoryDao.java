package gameHistory;

import config.db.DBConnect;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GameHistoryDao {
    private DBConnect dbConn;

    public GameHistoryDao() {
        this.dbConn = DBConnect.getInstance();
    }

    public int insert(int gameId, int playerId, int gameRoomId) {
        Connection conn = dbConn.conn();

        String sql = "INSERT INTO game_history VALUES (seq_game_history.NEXTVAL, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, gameId);
            pstmt.setInt(2, playerId);
            pstmt.setInt(3, gameRoomId);
            pstmt.setDate(4, Date.valueOf(LocalDate.now()));
            pstmt.setDate(5, Date.valueOf(LocalDate.now()));

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.disconnectDB(conn);
        }

        return -1;
    }

    public List<GameHistoryQueryVo> findByGameId(int gameId) {
        Connection conn = dbConn.conn();

        List<GameHistoryQueryVo> result = new ArrayList<>();

        String sql =
                "SELECT h.history_id AS historyId, g.game_name AS gameName, p.nickname AS nickname" +
                        " FROM game_history h" +
                        "    INNER JOIN game g" +
                        "        ON h.game_id = g.game_id" +
                        "    INNER JOIN player p" +
                        "        ON h.winner = p.player_id" +
                        " WHERE p.game_id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result.add(new GameHistoryQueryVo(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.disconnectDB(conn);
        }
        return result;
    }

    public List<GameHistoryQueryVo> findByPlayerId(String nickname) {
        Connection conn = dbConn.conn();
        List<GameHistoryQueryVo> result = new ArrayList<>();

        String sql =
                "SELECT h.history_id AS historyId, g.game_name AS gameName, p.nickname AS nickname" +
                        " FROM game_history h" +
                        "    INNER JOIN game g" +
                        "        ON h.game_id = g.game_id" +
                        "    INNER JOIN player p" +
                        "        ON h.winner = p.player_id" +
                        " WHERE nickname = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, nickname);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result.add(new GameHistoryQueryVo(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.disconnectDB(conn);
        }

        return result;
    }

    public int getGameRoomId() {
        Connection conn = dbConn.conn();
        List<GameHistoryQueryVo> result = new ArrayList<>();

        String sql =
                "SELECT seq_game_room_id.NEXTVAL FROM dual";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.disconnectDB(conn);
        }

        return -1;

    }



}
