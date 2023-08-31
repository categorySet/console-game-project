package project.shop;

import project.config.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseDao {
    private DBConnect dbconn;

    public PurchaseDao() {
        dbconn = DBConnect.getInstance();
    }

    // 구매
    public void insert(Purchase s) {
        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "insert into purchase values(seq_purchase.nextval,?,?,sysdate,sysdate)"; // item_id, player_id

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
    public Purchase select(int purchaseId) {
        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "select purchase_id, item_id, player_id, create_date, last_modified_date from purchase where purchase_id=?";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 매칭
            pstmt.setInt(1, purchaseId);

            // 5. 실행
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Purchase(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getDate(5), rs.getDate(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return null;
    }

    // player로 검색
    public ArrayList<Purchase> selectByPlayerId(int playerId) {
        ArrayList<Purchase> list = new ArrayList<Purchase>();

        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "select purchase_id, item_id, player_id, create_date, last_modified_date from purchase where player_id=?";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. ?매칭
            pstmt.setInt(1, playerId);

            // 5. 실행
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Purchase(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getDate(5), rs.getDate(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return list;
    }

    // 전체 검색
    public ArrayList<Purchase> selectAll() {

        ArrayList<Purchase> list = new ArrayList<Purchase>();

        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "select purchase_id, item_id, player_id, create_date, last_modified_date from purchase";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. 실행
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Purchase(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getDate(5), rs.getDate(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return list;
    }

    // 구매 취소
    public void delete(int purchaseId) {
        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "delete purchase where purchase_id=?"; // purchase_id

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 매칭
            pstmt.setInt(1, purchaseId);

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
