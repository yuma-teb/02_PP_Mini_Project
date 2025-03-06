package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private Connection conn = DatabaseConnection.getConnection();
    //for saving state when add and update

    //get products from db
    public List<Product> get() {
        try (Statement st = conn.createStatement()) {
            List<Product> products = new ArrayList<>();
            String sql = "select * from products;";
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("id"), resultSet.getString(2), resultSet.getDouble(3), resultSet.getInt(4), resultSet.getTimestamp(5).toLocalDateTime().toLocalDate()));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //get product by id
    public Product get(int id) {
        return null;
    }

    //get product by name
    public Product get(String name) {
        return null;
    }

    //delete product
    public void delete(int id) {

    }

    //save product
    public void saveProducts(List<Product> products) {

    }
    //update product
    public void updateProducts(List<Product> products) {

    }
}
