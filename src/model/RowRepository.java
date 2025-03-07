package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RowRepository {
    private Connection conn = DatabaseConnection.getConnection();
    //get
    public int get(){
        try(Statement st = conn.createStatement()){
            String sql = "select * from rows;";
            ResultSet resultSet = st.executeQuery(sql);
            int numToDisplay = 0;
            while (resultSet.next()){
                numToDisplay = resultSet.getInt(2);
            }
            return numToDisplay;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //update
    public int update(){
        return 1;
    }
}
