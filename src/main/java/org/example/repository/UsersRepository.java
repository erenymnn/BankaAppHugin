package org.example.repository;

import org.example.dbHelper.DataBaseConnection;
import org.example.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersRepository {

    public List<Users> getAll() {
        List<Users> usersList = new ArrayList<>();// Kullanıcıları saklamak için liste oluşturduk
        String sql = "SELECT * FROM users";

        try (Connection conn = DataBaseConnection.getConnection();//Bu blok sayesinde Connection ve PreparedStatement otomatik kapanır. try catch sayesinde
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");// id sütununu al
                String username = rs.getString("username");// username sütununu al
                int password = rs.getInt("password");// // password sütununu al

                Users user = new Users(id, username, password);//Users nesnesi oluştur
                usersList.add(user);// listeye ekle
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList; //tüm kullancıları döndür
    }


    public Users getById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";// Sorgu: id parametresi ile filtrele

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // ? yerine id değerini koy
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {// eğer satır varsa
                    int userId = rs.getInt("id");// id sütununu alıyor
                    String username = rs.getString("username");
                    int password = rs.getInt("password");

                    return new Users(userId, username, password); // kullanıcıyı döndürür
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Users getByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {  // sorguları calıştırır.
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String uname = rs.getString("username");
                    int password = rs.getInt("password");

                    return new Users(id, uname, password);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    public boolean insert(Users user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername()); // kullanıcı adını set et
            stmt.setInt(2, user.getPassword()); // şifreyi set et

            int rowsAffected = stmt.executeUpdate(); // sorguyu çalıştır
            return rowsAffected > 0;  // başarılıysa true döndür


        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

}

