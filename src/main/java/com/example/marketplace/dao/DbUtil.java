package com.example.marketplace.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {

    private static String url;
    private static String username;
    private static String password;

    static {
        try {
            // Load properties from db.properties on the classpath
            Properties props = new Properties();
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            try (InputStream in = cl.getResourceAsStream("db.properties")) {
                if (in == null) {
                    throw new RuntimeException("db.properties not found on classpath");
                }
                props.load(in);
            }

            url = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");

            // This is optional with modern drivers, but harmless:
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
