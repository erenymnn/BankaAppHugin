package org.example.repository;

import org.example.dbHelper.DataBaseConnection;
import org.example.model.Transactions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionsRepository {

    // Belirli bir hesabın işlem geçmişini listelemek

    public List<Transactions> getByAccountId(int accountId) {
        String sql = "SELECT * FROM transactions WHERE account_id = ?";
        List<Transactions> transactionsList = new ArrayList<>();

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, accountId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int accId = rs.getInt("account_id");
                    String type = rs.getString("type");
                    double amount = rs.getDouble("amount");
                    Timestamp createdAt = rs.getTimestamp("created_at");

                    transactionsList.add(new Transactions(id, accId, type, amount, createdAt));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactionsList;
    }



    // hesap kayıt ekleme
    public boolean insert(Transactions transactions) {
            String sql = "INSERT INTO transactions (account_id, type, amount, created_at) VALUES (?, ?, ?, ?)";

            try (Connection conn = DataBaseConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, transactions.getAccount_id());
                ps.setString(2, transactions.getType());
                ps.setDouble(3, transactions.getAmount());
                ps.setTimestamp(4, transactions.getCreatedAt());

                int rowsInserted = ps.executeUpdate();
                return rowsInserted > 0;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

    }


