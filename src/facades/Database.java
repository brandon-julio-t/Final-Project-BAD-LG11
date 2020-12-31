package facades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {

    private static final String USERNAME = "root"; // change with your MySQL username, the default username is 'root'
    private static final String PASSWORD = ""; // change with your MySQL password, the default password is empty
    private static final String DATABASE_NAME = "bad_teori"; // change with the database name that you use
    private static final String HOST = "localhost:3306"; // change with your MySQL host, the default port is 3306
    private static final String CONECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE_NAME);

    private static Database instance;

    private Connection connection;
    private Statement statement;

    public Database() {
        try {
            connection = DriverManager.getConnection(CONECTION, USERNAME, PASSWORD);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void executeUpdate(String query) {
        try {
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement prepareStatement(String query) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ps;
    }

    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
