package project.admin;

import project.config.DBConnect;
import project.player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    private DBConnect dbconn;

    public AdminDao() {
//        dbconn=DBConnect.getInstance();       //FIXME : 중복된 코드
        dbconn = DBConnect.getInstance();
    }

    public Player select(int playerId) {
        Connection conn = dbconn.conn();

        String sql = "select * from player where player_id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();

            //FIXME : 저는 Player 코드가 없네요??
            if (rs.next()) {
//                return new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getDate(6));
                return new Player();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return null;        //return null 추가
    }

    //수정
    public void update(int playerId) {
        Connection conn = dbconn.conn();


        /**FIXME : 쿼리가 잘못 되었습니다.
         * PlayerId로 크레딧을 수정하고자 한다면
         * update player set credit = ? where player_id = ? 하면 될거 같은데요?? 체크 부탁 드립니다.
         * */
        String sql = "update player set credit=? where player_id = (select credit from player)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

//            pstmt.setInt(1, credit);//player 테이블에서 가져올 크레딧       //FIXME : set -> setInt credit Param이 없어요
            pstmt.setInt(1, playerId);                    //paramIndex가 2가 되어야 할듯 합니다

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
    }
}
