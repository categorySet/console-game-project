package project.config.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Sequence {


    private DBConnect dbConnect;

    public Sequence() {
        this.dbConnect = DBConnect.getInstance();
    }

    public void createAll() {
        TableName[] tableNames = TableName.values();
        for (TableName tableName : tableNames) {
            create(tableName.name());
        }
    }

    public void dropAll() {
        TableName[] tableNames = TableName.values();
        for (TableName tableName : tableNames) {
            drop(tableName.name());
        }
    }

    public void create(String seqName) {
        Connection conn = dbConnect.conn();
        String query = "CREATE SEQUENCE seq_" +seqName;
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.disconnectDB(conn);
        }
    }

    public void drop(String seqName) {
        Connection conn = dbConnect.conn();
        String query = "DROP SEQUENCE seq_" +seqName;
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.disconnectDB(conn);
        }

    }


}
