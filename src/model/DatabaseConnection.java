package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static final String URL = "jdbc:postgresql://localhost:5432/javaminiProject";
  private static final String USER = "plongrotha";
  private static final String PASSWORD = "plongrotha11112003";

  public static Connection getConnection() {
    try {
      return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException e) {
      throw new RuntimeException("Error connecting to the database");
    }
  }

}
