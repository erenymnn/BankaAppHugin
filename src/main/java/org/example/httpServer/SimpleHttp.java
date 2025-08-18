package org.example.httpServer;

import org.example.dbHelper.DataBaseConnection;

import java.sql.Connection;

public class SimpleHttp {
    public static void main(String[] args) {
    try {
        Connection conn= DataBaseConnection.getConnection();
        System.out.println("Sunucu 8080 portunda calisiyor..");
    }catch (Exception e) {
        e.printStackTrace();
    }



    }
}
