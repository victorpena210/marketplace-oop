package com.example.marketplace.dao;

import com.example.marketplace.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    // Adjust column names if your users table uses different ones
    private static final String INSERT_SQL =
            "INSERT INTO users (email, password, full_name) VALUES (?, ?, ?)";

    private static final String FIND_BY_EMAIL_SQL =
            "SELECT id, email, password, full_name FROM users WHERE email = ?";

    public void save(User user) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     INSERT_SQL,
                     Statement.RETURN_GENERATED_KEYS
             )) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());   // later you can hash this
            ps.setString(3, user.getFullName());

            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // You can improve error handling later
        }
    }

    public User findByEmail(String email) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_EMAIL_SQL)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setEmail(rs.getString("email"));
                    u.setPassword(rs.getString("password"));
                    u.setFullName(rs.getString("full_name"));
                    return u;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // not found
    }
}
