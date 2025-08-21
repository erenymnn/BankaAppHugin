package org.example.repository;

import org.example.model.Accounts;
import org.example.dbHelper.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountsRepository {

    public Accounts getByUserId(int userId) {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM accounts WHERE user_id=?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Accounts(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateBalance(int userId, double newBalance) {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE accounts SET balance=? WHERE user_id=?")) {
            stmt.setDouble(1, newBalance);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean newAccountsinsert(Accounts account) {
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO accounts (user_id, balance) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, account.getUser_id());
            stmt.setDouble(2, account.getBalance());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) account.setId(keys.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




}
