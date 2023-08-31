package project.shop;

import project.config.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDao {
    private DBConnect dbconn;

    public OrderDao() {
        dbconn = DBConnect.getInstance();
    }

    // 구매
    public void insert(Order s) {
        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "insert into order values(seq_order.nextval,?,?,sysdate,sysdate)";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 매칭
            pstmt.setInt(1, s.getItemId());
            pstmt.setInt(2, s.getPlayerId());

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
    public Order select(int orderId) {
        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "select * from order where order_id=?";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 매칭
            pstmt.setInt(1, orderId);

            // 5. 실행
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getDate(5), rs.getDate(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return null;
    }

    // player로 검색
    public ArrayList<Order> selectByPlayerId(int playerId) {
        ArrayList<Order> list = new ArrayList<Order>();

        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "select * from order where player_id=?";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. ?매칭
            pstmt.setInt(1, playerId);

            // 5. 실행
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getDate(5), rs.getDate(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return list;
    }

    // 전체 검색
    public ArrayList<Order> selectAll() {

        ArrayList<Order> list = new ArrayList<Order>();

        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "select * from order";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. 실행
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getDate(5), rs.getDate(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return list;
    }

    // 구매 취소
    public void delete(int orderId) {
        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "delete order where order_id=?";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 매칭
            pstmt.setInt(1, orderId);

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
