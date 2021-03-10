package sample.DB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Client;

import java.sql.*;
import java.util.Arrays;

public class CRUDClient {

    public void criate (Client client){
        String sql = "INSERT INTO clients VALUES (?,?,?,?,?,?,?,?,?);";
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        int id;
        try {
            preparedStatement = ConnectorJDBC.getPreparedStatement(sql);
            statement = preparedStatement.getConnection().createStatement();
            try {
            ResultSet rs = statement.executeQuery("SELECT * FROM clients ORDER BY id DESC LIMIT 1");
            rs.next();
            id = rs.getInt(1)+1;
            } catch (SQLException throwables) {
                id = 1;
            }
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2,client.getLastName());
            preparedStatement.setString(3,client.getName());
            preparedStatement.setString(4,client.getSurName());
            preparedStatement.setDate(5, new Date(client.getDateOfBirth().getTime()));
            preparedStatement.setInt(6,client.getInn());
            preparedStatement.setString(7,client.getFamilyStatus());
            preparedStatement.setString(8,client.getTypeOfEducation());
            preparedStatement.setBoolean(9,client.getVip());
            preparedStatement.execute();
            System.out.println("Добавлен клиент в базу данных");
            ConnectorJDBC.preparedStatementClose(preparedStatement);
        } catch (SQLException throwables) {
            System.out.println("Ошибка при подключении к базе данных");
        }
    }

    public ObservableList<Client> reedAll (){
        String sql = "SELECT * FROM custome_accounting.clients";
        Statement statement =null;
        ObservableList<Client> clients = FXCollections.observableArrayList();
        try {
            statement = ConnectorJDBC.getStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("чтение из базы данных");
            while (rs.next()) {
                clients.add(new Client(rs.getLong(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getDate(5), rs.getInt(6),rs.getString(7),rs.getString(8),rs.getBoolean(9)));
            }
            rs.close();
            ConnectorJDBC.statementClose(statement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clients;
    }

    public void update (Client client){
        String sql = "UPDATE clients SET last_name=?,name=?,sur_name=?,date_of_birth=?,inn=?,family_status=?,type_of_education=?,vip=? WHERE id=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = ConnectorJDBC.getPreparedStatement(sql);
            preparedStatement.setLong(9, client.getId());
            preparedStatement.setString(1,client.getLastName());
            preparedStatement.setString(2,client.getName());
            preparedStatement.setString(3,client.getSurName());
            preparedStatement.setDate(4, new Date(client.getDateOfBirth().getTime()));
            preparedStatement.setInt(5,client.getInn());
            preparedStatement.setString(6,client.getFamilyStatus());
            preparedStatement.setString(7,client.getTypeOfEducation());
            preparedStatement.setBoolean(8,client.getVip());
            preparedStatement.executeUpdate();
            System.out.println("Добавлен клиент в базу данных");
            ConnectorJDBC.preparedStatementClose(preparedStatement);
        } catch (SQLException throwables) {
            System.out.println("Ошибка при подключении к базе данных");
        }
    }

    public void delete(int id){
        String sql = "DELETE FROM clients WHERE id=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = ConnectorJDBC.getPreparedStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

            ConnectorJDBC.preparedStatementClose(preparedStatement);
        } catch (SQLException throwables) {
            System.out.println("Ошибка при подключении к базе данных");
        }
    }

}
