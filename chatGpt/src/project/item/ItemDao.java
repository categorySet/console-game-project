package project.item;

import project.config.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDao {
    private DBConnect dbconn;
    public ItemDao() {
        dbconn = DBConnect.getInstance();
    }

    // TODO: Admin만 아이템 추가, 수정, 삭제 가능
    // 아이템 등록
    public void insert(Item i) {
        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "insert into item values(seq_item.nextval,?,?,?,?,?,sysdate,sysdate)";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 매칭
            pstmt.setString(1, i.getItemName());
            pstmt.setInt(2, i.getGameId());
            pstmt.setInt(3, i.getPrice());
            pstmt.setInt(4, i.getAmount());
            pstmt.setBoolean(5, i.isLimitedEdition());

            // 5. 실행
            int cnt = pstmt.executeUpdate();
            System.out.println(cnt + "개의 아이템이 등록되었습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
    }

    // 아이템 검색
    public Item select(int itemId) {
        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "select * from Item where item_id=?";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 매칭
            pstmt.setInt(1, itemId);

            // 5. 실행
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Item(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getBoolean(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return null;
    }

    // gameId로 검색
    public ArrayList<Item> selectByGameId(int gameId) {
        ArrayList<Item> list = new ArrayList<Item>();

        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "select * from item where game_id=?";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. ?매칭
            pstmt.setInt(1, gameId);

            // 5. 실행
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Item(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getBoolean(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return list;
    }

    // 전체 검색
    public ArrayList<Item> selectAll() {

        ArrayList<Item> list = new ArrayList<Item>();

        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "select * from item";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 5. 실행
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Item(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getBoolean(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return list;
    }

    // 아이템 수정
    public void update(Item i) {
        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "update item set price=?, amount=?, limitedEdition=?, lastModifiedDate=sysdate where itemId=?";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 매칭
            pstmt.setInt(1, i.getPrice());
            pstmt.setInt(2, i.getAmount());
            pstmt.setBoolean(3, i.isLimitedEdition());

            // 5. 실행
            int cnt = pstmt.executeUpdate();
            System.out.println(cnt + "개의 아이템이 수정되었습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
    }

    // 아이템 삭제
    public void delete(int itemId) {
        // 1. db연결
        Connection conn = dbconn.conn();

        // 2. sql 작성
        String sql = "delete item where item_id=?";

        try {
            // 3. preparedstatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // 4. ? 매칭
            pstmt.setInt(1, itemId);

            // 5. 실행
            int cnt = pstmt.executeUpdate();
            System.out.println(cnt + "개의 아이템이 삭제되었습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
    }
}
