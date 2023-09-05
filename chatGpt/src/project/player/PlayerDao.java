package project.player;

import project.config.db.DBConnect;
import project.item.Item;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlayerDao {
    private static DBConnect dbconn;

    public PlayerDao() {
        dbconn = DBConnect.getInstance();
    }

    public ArrayList<Player> findAll() {
        ArrayList<Player> result = new ArrayList<>();
        Connection conn = dbconn.conn();

        String sql = "SELECT player_id, login_id, password, nickname, credit, create_date, last_modified_date FROM player";
        try {
            PreparedStatement prepared = conn.prepareStatement(sql);
            ResultSet rs = prepared.executeQuery();

            while (rs.next()) {
                result.add(new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6), rs.getDate(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return result;
    }

    public void signup(Player player) {
        System.out.println("===== 회원 가입 ======");
        if (isDuplicatedLoginId(player.getLoginId())) {
            System.out.println("중복된 아이디 입니다.");
        } else {
            if (!player.getPassword().equals(player.getConfirmPassword())) {
                System.out.println("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            } else {
                Connection conn = dbconn.conn();
                String query = "insert into player values(seq_player.nextval, ?, ?, ?, ?, ?, ?)";     //player_id, login_id, password, nickname, credit, c_date, m_date
                try {
                    PreparedStatement prepared = conn.prepareStatement(query);
                    prepared.setString(1, player.getLoginId());
                    prepared.setString(2, player.getPassword());
                    prepared.setString(3, player.getNickname());
                    prepared.setInt(4, 10000);
                    prepared.setDate(5, Date.valueOf(LocalDate.now()));
                    prepared.setDate(6, Date.valueOf(LocalDate.now()));
                    prepared.executeUpdate();

                    System.out.println("회원 가입이 완료되었습니다.");

                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    dbconn.disconnectDB(conn);
                }
            }
        }

    }

    public boolean isDuplicatedLoginId(String loginId) {        //회원가입 시 아이디가 중복되었는지 여부를 판단
        Connection conn = dbconn.conn();
        String query = "select login_id from player where login_id = ?";
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.setString(1, loginId);

            ResultSet rs = prepared.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return false;
    }

    public boolean validatePwd(String loginId, String pwd) {        //DB에 저장된 로그인 아이디와 비밀번호가 일치하는지 여부를 판단
        Connection conn = dbconn.conn();
        String query = "select login_id, password from player where login_id = ?";
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.setString(1, loginId);
            ResultSet rs = prepared.executeQuery();

            if (rs.next()) {
                if (pwd.equals(rs.getString(2))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
        return false;
    }

    public Player findByLoginId(String loginId) {
        Connection conn = dbconn.conn();
        String query = "select player_id, login_id, password, nickname, credit, create_date, last_modified_date" +
                " from player where login_id = ?";

        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.setString(1, loginId);
            ResultSet rs = prepared.executeQuery();

            if (rs.next()) {
                return new Player(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getDate(6), rs.getDate(7));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateNickname(Player player) {
        Connection conn = dbconn.conn();
        String query = "update player set nickname = ?, last_modified_date = ? where player_id = ?";
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.setString(1, player.getNickname());         //여기서 player.getNickname은 새로운 nickname
            prepared.setDate(2, player.getLastModifiedDate());
            prepared.setInt(3, player.getPlayerId());
            prepared.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
    }

    public void updatePassword(Player player) {
        if (!player.getPassword().equals(player.getConfirmPassword())) {        //여기의 getPassword()는 newPwd임
            System.out.println("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        } else {
            Connection conn = dbconn.conn();
            String query = "update player set password = ?, last_modified_date = ? where player_id = ?";
            try {
                PreparedStatement prepared = conn.prepareStatement(query);
                prepared.setString(1, player.getPassword());
                prepared.setDate(2, player.getLastModifiedDate());
                prepared.setInt(3, player.getPlayerId());
                prepared.executeUpdate();
                System.out.println("비밀번호 변경이 완료되었습니다.");

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dbconn.disconnectDB(conn);
            }
        }
    }

    public synchronized void updateCredit(Player player, int newCredit) {
        Connection conn = dbconn.conn();
        String query = "update player set credit = (select credit + ? from player where player_id = ?) " +
                "where player_id = ?";          //추가할 credit, player_id, player_id
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.setInt(1, newCredit);
            prepared.setInt(2, player.getPlayerId());
            prepared.setInt(3, player.getPlayerId());
            prepared.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
    }


    public void delete(Player player) {
        Connection conn = dbconn.conn();
        String query = "delete player where player_id=?";

        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.setInt(1, player.getPlayerId());
            prepared.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }
    }


    public synchronized Player findByNickname(String nickname) {

        Connection conn = dbconn.conn();
        String query = "select player_id, login_id, nickname, credit, create_date, last_modified_date from player where nickname = ?";
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.setString(1, nickname);
            ResultSet rs = prepared.executeQuery();

            if(rs.next()) {
                return new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDate(5), rs.getDate(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbconn.disconnectDB(conn);
        }

        return null;

    }

}
