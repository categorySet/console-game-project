package project.player;

import project.config.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDao {
    private static DBConnect dbconn = new DBConnect();

    // 회원 가입 예시 query가 Table과 맞지않으니 참고만 할것
    public void signup(String id, String pwd, String confirmPwd, String name, String email) {
        System.out.println("===== 회원 가입 ======");
        if (isDuplicatedLoginId(id)) {
            System.out.println("중복된 아이디 입니다.");
        } else {
            if (!pwd.equals(confirmPwd)) {
                System.out.println("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            } else {
                Connection conn = dbconn.conn();
                String query = "insert into member values(?, ?, ?, ?)";
                try {
                    PreparedStatement prepared = conn.prepareStatement(query);
                    prepared.setString(1, id);
                    prepared.setString(2, pwd);
                    prepared.setString(3, name);
                    prepared.setString(4, email);
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

    public boolean isDuplicatedLoginId(String id) {
        Connection conn = dbconn.conn();
        String query = "select player_id from player where player_id = ?";
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.setString(1, id);

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
            System.out.println("로그인에 성공했습니다.");
            return true;
        } else {
            return false;
        }
    }

    public boolean validatePwd(String id, String pwd) {
        return false;
    }










}
