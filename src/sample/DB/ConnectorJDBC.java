package sample.DB;

import javafx.collections.ObservableList;
import sample.model.Client;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static sample.Config.*;

public class ConnectorJDBC {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";


    public static Statement getStatement() throws SQLException {
        Statement statement = null;
        Connection connection = null;
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Подключение к базе данных");
        statement = connection.createStatement();
        System.out.println("Создание Statement ");
        return statement;
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        System.out.println("Подключение к базе данных");
        preparedStatement = connection.prepareStatement(sql);
        System.out.println("Cоздание Prepared Statement ");
        return preparedStatement;
    }

    public static void statementClose(Statement statement) {
        try {
            statement.getConnection().close();
            statement.close();
            System.out.println("Подключкние закрыто");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void preparedStatementClose(PreparedStatement preparedStatement) {
        try {
            preparedStatement.getConnection().close();
            preparedStatement.close();
            System.out.println("Подключкние закрыто");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Boolean checkTables(){
        String query="SELECT * FROM clients";
        try {
            Statement statement = getStatement();
            ResultSet rs=statement.executeQuery(query);
                System.out.println(">>>>>>>>>>>Таблица существует<<<<<<<<<<<<<<<<<<");
                statementClose(statement);
                return true;
        } catch (SQLException throwables) {
            System.out.println(">>>>>>>>>>>Таблица не существует<<<<<<<<<<<<<<<<<<");
        }

        return false;
    }
    public static void criateNewTeble(){
        if (!checkTables()){
            String sql = "CREATE TABLE clients (id INT NOT NULL AUTO_INCREMENT,last_name VARCHAR(45) NOT NULL,name VARCHAR(45) NOT NULL,sur_name VARCHAR(45) NOT NULL,date_of_birth DATE NOT NULL,inn INT NOT NULL,family_status VARCHAR(45) NOT NULL,type_of_education VARCHAR(45) NOT NULL,vip TINYINT NOT NULL,PRIMARY KEY (id));";
            Statement statement = null;
            try {
                statement = ConnectorJDBC.getStatement();
                statement.execute(sql);
                statementClose(statement);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
