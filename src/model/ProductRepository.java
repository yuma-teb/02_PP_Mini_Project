package model;

import utils.Helper;
import error.NotFoundException;
import query.QueryOperator;
import query.SelectQueryBuilder;
import query.TableName;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private Connection conn = DatabaseConnection.getConnection();

    //get products from db
    public List<Product> get() {
        try (Statement st = conn.createStatement()) {
            List<Product> products = new ArrayList<>();
            String sql = "select * from products order by id;";
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

        try (Statement st = conn.createStatement()) {
            try (ResultSet resultSet = st.executeQuery(sql.buildQuery())) {
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
            System.out.println(Helper.returnStringColor(e.getMessage(), Helper.RED));
            return null;  // or rethrow e if needed
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //get product by name
    public List<Product> get(String name) {
        SelectQueryBuilder sql = new SelectQueryBuilder(TableName.products)
                                .where("name", "%" + name + "%", QueryOperator.ILIKE);

        try(PreparedStatement st = conn.prepareStatement(sql.buildQuery() + " order by id")) {
            List<Product> products = new ArrayList<>();
           try(ResultSet resultSet = st.executeQuery()) {
               if(!resultSet.isBeforeFirst()) {
                   throw new NotFoundException("There is no product with that name!!");
               }

               while (resultSet.next()) {
                   products.add(new Product(
                           resultSet.getInt("id"),
                           resultSet.getString("name"), // Use column name instead of index
                           resultSet.getDouble("unitPrice"),
                           resultSet.getInt("qty"),
                           resultSet.getTimestamp("importDate").toLocalDateTime().toLocalDate()
                   ));
               }
               return products;
           } catch (NotFoundException e) {
               System.out.println(Helper.returnStringColor(e.getMessage(), Helper.RED));
               return null;
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //delete product
    public void delete(int id) {
        String sql = "delete from products where id = ?";

        try(PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);

             st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //save product
    public void saveProducts(List<Product> products) {
        try {
            conn.setAutoCommit(false);
            products.forEach(product -> {
                String sql = "INSERT INTO products (name, unitPrice, qty, importDate) VALUES (?, ?, ?, ?)";

                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, product.getName());
                    st.setBigDecimal(2, new BigDecimal(product.getUnitPrice())); // Use BigDecimal for NUMERIC(10, 2)
                    st.setInt(3, Integer.parseInt(product.getQty()));
                    st.setDate(4, java.sql.Date.valueOf(product.getImportDate()));

                    st.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Transaction rolled back due to error.");
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace(); // Log rollback failure
            }
            throw new RuntimeException("Transaction failed", e);
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //update product
    public void updateProducts(List<Product> products) {
        try {
            conn.setAutoCommit(false);
            products.forEach(product -> {
                String sql = "UPDATE products SET name = ?, unitPrice = ?, qty = ? WHERE id = ?";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, product.getName());
                    st.setBigDecimal(2, new BigDecimal(product.getUnitPrice())); // Use BigDecimal for NUMERIC(10, 2)
                    st.setInt(3, Integer.parseInt(product.getQty()));
                    st.setInt(4, product.getId());

                    st.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Transaction rolled back due to error.");
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace(); // Log rollback failure
            }
            throw new RuntimeException("Transaction failed", e);
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
