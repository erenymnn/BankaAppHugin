package org.example.repository;

import org.example.dbHelper.DataBaseConnection;
import org.example.model.Accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountsRepository {
//(para yatırma, çekme vb.) için yapıyoruz burayı


    public Accounts getByUserId(int userId) {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";

        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    int user_id = rs.getInt("user_id");
                    double balance = rs.getDouble("balance");
                    return new Accounts(id, user_id, balance);
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateBalance(int accountId, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";

        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, newBalance);// yeni bakiye
            ps.setInt(2, accountId);// güncellenecek hesap id

            int rowsUpdated = ps.executeUpdate(); // güncellenen satır sayısını al
            return rowsUpdated > 0;// 1 veya daha fazla satır güncellendiyse true döner



        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean insert(Accounts accounts) {
        String sql = "INSERT INTO accounts (user_id, balance) VALUES (?, ?)";

        try(Connection conn = DataBaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1,accounts.getUser_id());
            ps.setDouble(2,accounts.getBalance());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;


        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

}
