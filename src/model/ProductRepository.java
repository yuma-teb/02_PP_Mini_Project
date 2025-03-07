package model;

import Utils.Helper;
import error.NotFoundException;
import query.SelectQueryBuilder;
import query.TableName;

import java.sql.*;
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
        // query
        SelectQueryBuilder sql = new SelectQueryBuilder(TableName.products)
                .where("id", id);

        try (PreparedStatement st = conn.prepareStatement(sql.buildQuery())) {
            System.out.println("build query: " + sql.buildQuery());

            try (ResultSet resultSet = st.executeQuery()) {
                if (!resultSet.next()) {
                    throw new NotFoundException("Product not found");
                }

                return new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4),
                        resultSet.getTimestamp(5).toLocalDateTime().toLocalDate()
                );
            }
        } catch (NotFoundException e) {
            System.out.println(Helper.RED + e.getMessage() + Helper.RESET);
            return null;  // or rethrow e if needed
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
