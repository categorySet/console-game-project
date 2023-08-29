package project.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static DBConnect dbconn = new DBConnect();
//    private String url = "jdbc:oracle:thin:@localhost:49161/xe";
    private String url = "jdbc:oracle:thin:@localhost:1521/xe";        //TODO : 반드시 자기의 포트와 데이터베이스로 연결


    public DBConnect() {
    }

    public Connection conn() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn =  DriverManager.getConnection(url, "hr", "hr");
//            conn =  DriverManager.getConnection(url, "game", "game");             //TODO : 준회 씨 전용 DB 아이디, 비번 Clone하면

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static DBConnect getInstance() {
        return dbconn;
    }

    public void disconnectDB(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
