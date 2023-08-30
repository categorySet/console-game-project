package project.player;

import project.config.DBConnect;

import java.sql.*;
import java.time.LocalDate;

public class PlayerDao {
    private static DBConnect dbconn;

    public PlayerDao() {
        dbconn = DBConnect.getInstance();
    }

    public void signup(String loginId, String pwd, String confirmPwd, String nickname) {
        System.out.println("===== 회원 가입 ======");
        if (isDuplicatedLoginId(loginId)) {
            System.out.println("중복된 아이디 입니다.");
        } else {
            if (!pwd.equals(confirmPwd)) {
                System.out.println("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            } else {
                Connection conn = dbconn.conn();
                String query = "insert into player values(seq_player.nextval, ?, ?, ?, ?, ?, ?)";     //player_id, login_id, password, nickname, credit, c_date, m_date
                try {
                    PreparedStatement prepared = conn.prepareStatement(query);
                    prepared.setString(1, loginId);
                    prepared.setString(2, pwd);
                    prepared.setString(3, nickname);
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

    public boolean isDuplicatedLoginId(String loginId) {
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

    public boolean validateLogin(String loginId, String password) {
        if(validatePwd(loginId, password)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validatePwd(String loginId, String pwd) {
        Connection conn = dbconn.conn();
        String query = "select login_id, password from player where login_id = ?";
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.setString(1, loginId);
            ResultSet rs = prepared.executeQuery();

            if(rs.next()) {
                if(pwd.equals(rs.getString(2))) {
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

            if(rs.next()) {
                return new Player(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getDate(6), rs.getDate(7));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
