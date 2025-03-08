package model;

import java.sql.*;

public class RowRepository {
    private Connection conn = DatabaseConnection.getConnection();

    //get
    public int get() {
        try (Statement st = conn.createStatement()) {
            String sql = "select * from row;";
            ResultSet resultSet = st.executeQuery(sql);
            int numToDisplay = 0;
            while (resultSet.next()) {
                numToDisplay = resultSet.getInt(2);
            }
            return numToDisplay;

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    //update
    public int setRow(int n) {
        int defaultRowId = 1;
        try (PreparedStatement st = conn.prepareStatement("update row set row_to_display = ? where id=?;")) {
            st.setInt(1, n);
            st.setInt(2, 1);
            int i = st.executeUpdate();
            if (i == 1) {
                return n;
            }
            //0 mean update was not success because zero row affected
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
