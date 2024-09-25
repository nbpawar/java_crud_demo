package java_crud_demo;

import java.sql.*;

public class CRUDOperations {

    // Database connection constants
    private static final String URL = "jdbc:mysql://localhost:3306/mydb"; // Replace 'mydb' with your DB name
    private static final String USER = "root"; // Replace with your DB username
    private static final String PASS = "password"; // Replace with your DB password

    public static void main(String[] args) {  
        //git Example operations
        insertUser("neta", "john.doe@example.com", "USA");
        getUsers();
        updateUser(1, "John Doe Updated", "john.updated@example.com", "Canada");
        deleteUser(1);
    }

    // CREATE operation
    public static void insertUser(String name, String email, String country) {
        String INSERT_SQL = "INSERT INTO users (name, email, country) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
             
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, country);

            int row = preparedStatement.executeUpdate();
            System.out.println(row + " user(s) inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ operation
    public static void getUsers() {
        String SELECT_SQL = "SELECT * FROM users";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Country: " + country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE operation
    public static void updateUser(int id, String name, String email, String country) {
        String UPDATE_SQL = "UPDATE users SET name = ?, email = ?, country = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, country);
            preparedStatement.setInt(4, id);

            int row = preparedStatement.executeUpdate();
            System.out.println(row + " user(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE operation
    public static void deleteUser(int id) {
        String DELETE_SQL = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {

            preparedStatement.setInt(1, id);
            int row = preparedStatement.executeUpdate();
            System.out.println(row + " user(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
