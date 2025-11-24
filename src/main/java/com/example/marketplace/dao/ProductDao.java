package com.example.marketplace.dao;

import com.example.marketplace.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private static final String SELECT_ALL =
            "SELECT id, name, category, price FROM products ORDER BY id";

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price")
                );
                products.add(p);
            }

        } catch (SQLException e) {
            // for now just log; later you can wrap in a custom exception
            e.printStackTrace();
        }

        return products;
    }
}
