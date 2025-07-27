package com.dennis.functionaltestdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyUtil {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/functional_test_db";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    public static String getGreeting() {
        return "Hello, World!";
    }

    public static String getBookSN() {
        System.out.println("connecting to database at " + DB_URL);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet res = null;
        String result = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            String sqlQuery = "SELECT * FROM book WHERE sn = ?";
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, "123");

            int count = 0;
            int second = 1;

            while (second <= 60) {
                System.out.println(second);
                try{
                    res = pstmt.executeQuery();
                    while(res.next()) {
                        if(res.getString("sn")!=null){
                            result = res.getString("sn");
                            System.out.println("Book SN: " + result);
                            break;
                        }
                    }
                    res.close();
                    if(result != null) {
                        break;
                    }
                    System.out.println("Book SN not found, retrying...");
                } catch (Exception e) {
                    System.out.println("Error executing query: " + e.getMessage());
                }
                second++;
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Connection closed.");
                }
            } catch (Exception ex) {
                System.out.println("Error closing connection: " + ex.getMessage());
            }
        }

        return result;
    }

}
