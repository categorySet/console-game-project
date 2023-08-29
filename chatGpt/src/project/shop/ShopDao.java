package project.shop;

import project.config.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShopDao {
    private DBConnect dbconn;

    public ShopDao() {
        dbconn = DBConnect.getInstance();
    }

    // 구매
    public void insert(Shop s) {
        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "insert into shop values(seq_shop.nextval,?,?,?,sysdate,sysdate)"; // TODO: modifiedDate 수정

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 매칭
            pstmt.setInt(1, s.getItemId());
            pstmt.setInt(2, s.getPlayerId());
            pstmt.setBoolean(3, s.isLimitedEdition());

            // 5. 실행
            int cnt = pstmt.executeUpdate();
            System.out.println(cnt + "건의 구매가 완료되었습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
    }

    // 번호로 검색
    public Shop select(int shopId) {
        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "select * from shop where shop_id=?";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 매칭
            pstmt.setInt(1, shopId);

            // 5. 실행
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Shop(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getDate(5), rs.getDate(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return null;
    }

    // player로 검색
    public ArrayList<Shop> selectByPlayerId(int playerId) {
        ArrayList<Shop> list = new ArrayList<Shop>();

        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "select * from shop where player_id=?";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. ?매칭
            pstmt.setInt(1, playerId);

            // 5. 실행
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Shop(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getDate(5), rs.getDate(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return list;
    }

    // 전체 검색
    public ArrayList<Shop> selectAll() {

        ArrayList<Shop> list = new ArrayList<Shop>();

        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "select * from shop";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. 실행
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Shop(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getDate(5), rs.getDate(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return list;
    }

    // 구매 취소
    public void delete(int shopId) {
        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "delete shop where shop_id=?";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 매칭
            pstmt.setInt(1, shopId);

            // 5. 실행
            int cnt = pstmt.executeUpdate();
            System.out.println(cnt + "건의 구매가 취소되었습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
    }
}
