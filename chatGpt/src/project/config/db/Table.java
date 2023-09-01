package project.config.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Table {

    private DBConnect dbConnect;

    public Table() {
        this.dbConnect = DBConnect.getInstance();
    }

    public void createAll() {
        player();
        admin();
        blacklist();
        game();
        gameHistory();
        item();
        purchase();
    }

    public void dropAll() {
        TableName[] tableNames = TableName.values();
        for (TableName tableName : tableNames) {
            drop(tableName.name());
        }
    }

    public void player() {
        Connection conn = dbConnect.conn();
        String query =  "CREATE TABLE player(" +
                "player_id NUMBER PRIMARY KEY," +
                "login_id VARCHAR2(20) NOT NULL," +
                "password VARCHAR2(30) NOT NULL," +
                "nickname VARCHAR2(20) NOT NULL," +
                "credit NUMBER DEFAULT 0," +
                "create_date DATE," +
                "last_modified_date DATE)";
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
            System.out.println("Create Table Player");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.disconnectDB(conn);
        }
    }

    public void admin() {
        Connection conn = dbConnect.conn();
        String query =  "CREATE TABLE admin(" +
                "admin_id NUMBER PRIMARY KEY," +
                "player_id NUMBER," +
                "pwd NUMBER NOT NULL," +
                "black_list_id NUMBER," +
                "create_date DATE," +
                "last_modified_date DATE," +
                "CONSTRAINT fk_player_id FOREIGN KEY(player_id) REFERENCES player(player_id))";
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
            System.out.println("Create Table Admin");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.disconnectDB(conn);
        }
    }

    //TODO : FK
    public void blacklist() {
        Connection conn = dbConnect.conn();
        String query =  "CREATE TABLE blacklist(" +
                "black_list_id NUMBER PRIMARY KEY," +
                "reason VARCHAR2(50)," +
                "create_date DATE," +
                "last_modified_date DATE)";
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
            System.out.println("Create Table Blacklist");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.disconnectDB(conn);
        }
    }

    public void game() {
        Connection conn = dbConnect.conn();
        String query =  "CREATE TABLE game(" +
                "game_id NUMBER PRIMARY KEY," +
                "game_name VARCHAR2(50)," +
                "create_date DATE," +
                "last_modified_date DATE)";
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Create Table Game");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.disconnectDB(conn);
        }
    }

    public void gameHistory() {
        Connection conn = dbConnect.conn();
        String query =  "CREATE TABLE game_history(" +
                "history_id NUMBER PRIMARY KEY," +
                "game_id NUMBER NOT NULL," +
                "winner NUMBER," +
                "game_room_id NUMBER NOT NULL," +
                "create_date DATE," +
                "last_modified_date DATE," +
                "CONSTRAINT fk_game_id FOREIGN KEY(game_id) REFERENCES game(game_id)," +
                "CONSTRAINT fk_winner FOREIGN KEY(winner) REFERENCES player(player_id))";
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
            System.out.println("Create Table GameHistory");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.disconnectDB(conn);
        }
    }

    public void item() {
        Connection conn = dbConnect.conn();
        String query =  "CREATE TABLE item(" +
                "item_id NUMBER PRIMARY KEY," +
                "item_name VARCHAR2(50) NOT NULL," +
                "game_id NUMBER," +
                "price NUMBER," +
                "limited_edition CHAR(1) DEFAULT '0'," +
                "amount NUMBER," +
                "item_info VARCHAR2(100) NOT NULL," +
                "create_date DATE," +
                "last_modified_date DATE)";
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
            System.out.println("Create Table item");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.disconnectDB(conn);
        }
    }

    public void purchase() {
        Connection conn = dbConnect.conn();
        String query =  "CREATE TABLE purchase(" +
                "purchase_id NUMBER PRIMARY KEY," +
                "item_id NUMBER NOT NULL," +
                "player_id NUMBER NOT NULL," +
                "create_date DATE," +
                "last_modified_date DATE," +
                "CONSTRAINT fk_item_id FOREIGN KEY(item_id) REFERENCES item(item_id)," +
                "CONSTRAINT fk_player_id_shop FOREIGN KEY(player_id) REFERENCES player(player_id) ON DELETE CASCADE)";
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
            System.out.println("Create Table purchase");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.disconnectDB(conn);
        }
    }

    public void drop(String tableName) {
        Connection conn = dbConnect.conn();
        String query = "drop table " +tableName + " CASCADE CONSTRAINTS";
        try {
            Statement statement = conn.createStatement();
            statement.execute(query);
            System.out.println("Drop Table " +tableName);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnect.disconnectDB(conn);
        }
    }


}
