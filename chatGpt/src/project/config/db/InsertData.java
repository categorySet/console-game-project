package project.config.db;

import project.item.BasicItem;
import project.item.MafiaItem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class InsertData {

    private DBConnect dbConnect;

    public InsertData() {
        this.dbConnect = DBConnect.getInstance();
    }

    public void run() {
        player();
        game();
        item();
    }

    //    player, manager, blacklist, game, game_history, item, purchase;
    public void player() {
        Connection conn = dbConnect.conn();
        String query="INSERT INTO player VALUES (seq_player.NEXTVAL, ?, ?, ?, ?, ?, ?)";   //player_id, login_id, password, nickname, credit, c_date, m_date
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            addPlayerBatch(prepared, "gmg", "1234", "구민규", 100000);
            addPlayerBatch(prepared, "rgh", "1234", "류가희", 10000);
            addPlayerBatch(prepared, "kjh", "1234", "김준회", 10000);
            addPlayerBatch(prepared, "ksy", "1234", "김수연", 10000);
            addPlayerBatch(prepared, "test", "1234", "testUser", 10000);
            addPlayerBatch(prepared, "kosta", "1234", "kosta", 10000);
            addPlayerBatch(prepared, "kosta", "1234", "kosta", 10000);
            addPlayerBatch(prepared, "badUser", "1234", "BlackList", 10000);

            prepared.executeBatch();
            System.out.println("insert player");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbConnect.disconnectDB(conn);
        }
    }

    public void addPlayerBatch(PreparedStatement prepared, String loginId, String password, String nickname, int credit) throws SQLException {
            prepared.setString(1, loginId);
            prepared.setString(2, password);
            prepared.setString(3, nickname);
            prepared.setInt(4, credit);
            prepared.setDate(5, Date.valueOf(LocalDate.now()));
            prepared.setDate(6, Date.valueOf(LocalDate.now()));
            prepared.addBatch();
    }

    public void manager() {
        Connection conn = dbConnect.conn();
        String query="insert into player values (seq_manager.NEXTVAL, ?, ?, ?, ?, ?)";   //manager_id, player_id, pin, black_list_id, c_date, m_date
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.setInt(1, 1);
            prepared.setInt(2, 9876);
//            prepared.setInt(3, 1);        //TODO : 테이블 변경하기
            prepared.setDate(4, Date.valueOf(LocalDate.now()));
            prepared.setDate(5, Date.valueOf(LocalDate.now()));
            prepared.executeUpdate();

            System.out.println("insert manager");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbConnect.disconnectDB(conn);
        }
    }

    public void blacklist() {
        Connection conn = dbConnect.conn();
        String query="INSERT INTO blacklist VALUES (seq_blacklist.NEXTVAL, ?, ?, ?, ?)";   //black_list_id, player_id, reason, c_date, m_date
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            addBlacklistBatch(prepared, 8, "욕설");

            prepared.executeBatch();

            System.out.println("insert blacklist");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbConnect.disconnectDB(conn);
        }
    }

    private void addBlacklistBatch(PreparedStatement prepared, int playerId, String reason) throws SQLException {
        prepared.setInt(1, playerId);
        prepared.setString(2, reason);
        prepared.setDate(3, Date.valueOf(LocalDate.now()));
        prepared.setDate(4, Date.valueOf(LocalDate.now()));
        prepared.addBatch();
    }

    public void game() {
        Connection conn = dbConnect.conn();
        String query="INSERT INTO game VALUES (seq_game.NEXTVAL, ?, ?, ?)";   //game_id, game_name, c_date, m_date

        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.setString(1,"Mafia");
            prepared.setDate(2, Date.valueOf(LocalDate.now()));
            prepared.setDate(3, Date.valueOf(LocalDate.now()));
            prepared.executeUpdate();

            System.out.println("insert game");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbConnect.disconnectDB(conn);
        }
    }

    public void item() {
        Connection conn = dbConnect.conn();
        //item_id, item_name, game_id, category, price, limited_edition, amount, item_info c_date, m_date
        String query="INSERT INTO item VALUES (seq_player.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            addItemBatch(prepared, "빨간색", 0, BasicItem.color.getContents(), 1000, false, 999999, "닉네임을 빨간색으로 변경");
            addItemBatch(prepared, "파란색", 0, BasicItem.color.getContents(), 1000, false, 999999, "닉네임을 파란색으로 변경");
            addItemBatch(prepared, "노란색", 0, BasicItem.color.getContents(), 1000, false, 999999, "닉네임을 노란색으로 변경");
            addItemBatch(prepared, "초록색", 0, BasicItem.color.getContents(), 1000, false, 999999, "닉네임을 초록색으로 변경");

            addItemBatch(prepared, "멋진", 0, BasicItem.title.getContents(), 1000, false, 999999, "닉네임에 칭호 추가");
            addItemBatch(prepared, "예쁜", 0, BasicItem.title.getContents(), 1000, false, 999999, "닉네임에 칭호 추가");
            addItemBatch(prepared, "착한", 0, BasicItem.title.getContents(), 1000, false, 999999, "닉네임에 칭호 추가");
            addItemBatch(prepared, "나쁜", 0, BasicItem.title.getContents(), 1000, false, 999999, "닉네임에 칭호 추가");

            addItemBatch(prepared, MafiaItem.Mafia.getContents(), 1, MafiaItem.Mafia.getContents(), 2000, false, 999999, "마피아 역할 잠금해제");
            addItemBatch(prepared, MafiaItem.Doctor.getContents(), 1, MafiaItem.Doctor.getContents(), 2000, false, 999999, "의사 역할 잠금해제");
            addItemBatch(prepared, MafiaItem.Police.getContents(), 1, MafiaItem.Police.getContents(), 2000, false, 999999, "경찰 역할 잠금해제");
            prepared.executeBatch();

            System.out.println("insert item");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbConnect.disconnectDB(conn);
        }
    }

    private void addItemBatch(PreparedStatement prepared, String itemName, int gameId, String category, int price, boolean isLimited, int amount, String info) throws SQLException {
        prepared.setString(1, itemName);
        prepared.setInt(2, gameId);
        prepared.setString(3, category);
        prepared.setInt(4, price);
        prepared.setBoolean(5, isLimited);
        prepared.setInt(6, amount);
        prepared.setString(7, info);
        prepared.setDate(8, Date.valueOf(LocalDate.now()));
        prepared.setDate(9, Date.valueOf(LocalDate.now()));
        prepared.addBatch();
    }
}
