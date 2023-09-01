package project.config.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {

    private DBConnect dbConnect;

    public InsertData() {
        this.dbConnect = DBConnect.getInstance();
    }

    //    player, admin, blacklist, game, game_history, item, purchase;
    public void player() {
        Connection conn = dbConnect.conn();
        String query="insert into values ()";
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.addBatch();

            prepared.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbConnect.disconnectDB(conn);
        }

    }

    public void admin() {
        Connection conn = dbConnect.conn();
        String query="";
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.addBatch();

            prepared.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbConnect.disconnectDB(conn);
        }


    }

    public void blacklist() {
        Connection conn = dbConnect.conn();
        String query="";
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.addBatch();

            prepared.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbConnect.disconnectDB(conn);
        }


    }

    public void game() {
        Connection conn = dbConnect.conn();
        String query="";
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.addBatch();

            prepared.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbConnect.disconnectDB(conn);
        }


    }

    public void gameHistory() {
        Connection conn = dbConnect.conn();
        String query="";
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.addBatch();

            prepared.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbConnect.disconnectDB(conn);
        }


    }

    public void item() {
        Connection conn = dbConnect.conn();
        String query="";
        try {
            PreparedStatement prepared = conn.prepareStatement(query);
            prepared.addBatch();

            prepared.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbConnect.disconnectDB(conn);
        }


    }





}
