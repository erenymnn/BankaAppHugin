package org.example.repository;

import org.example.dbHelper.DataBaseConnection;
import org.example.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRepository {


    public Users getByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {  // runs queries.
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String uname = rs.getString("username");
                    String password = rs.getString("password");

                    return new Users(id, uname, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    //add new user
    public boolean insert(Users user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername()); // set username
            stmt.setString(2, user.getPassword()); // set password

            int rowsAffected = stmt.executeUpdate();  // run query
            return rowsAffected > 0;  // return true if successful


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Users getById(int userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    return new Users(id, username, password);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // kullanıcı bulunamazsa null döner
    }

}
