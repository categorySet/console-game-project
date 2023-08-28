package project.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static DBConnect dbconn = new DBConnect();
    private String url = "jdbc:oracle:thin:@localhost:49161/xe";        //TODO : 반드시 자기의 포트와 데이터베이스로 연결
//    private String url = "jdbc:oracle:thin:@localhost:1521/xe";

    public DBConnect() {
    }

    public Connection conn() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");                  //load driver 클래스 이름으로 찾아서 메모리에 얹음
            conn =  DriverManager.getConnection(url, "hr", "hr");    //login db, return connection object
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
