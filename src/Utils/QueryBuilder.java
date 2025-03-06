package Utils;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
    private List<String> columns;
//    private List<String> tables;
    private String table;
    private List<String> conditions;
    private String query;

    public QueryBuilder() {
        this.columns = new ArrayList<>();
        this.conditions = new ArrayList<String>();
    }

    /**
     * a function which is set the column to query from
     * @param column
     * @return
     */
    public QueryBuilder select (String ...column) {
        for(String col : column) {
            this.columns.add(col);
        }
        return this;
    }

    /**
     * a function to set table to query from
     * @param table: table name
     * @return: query builder
     */
    public QueryBuilder from(String table) {
        this.table = table;
        return this;
    }

    /**
     * a function to give condition to query select
     * @param condition
     * @return
     */
    public QueryBuilder where (String ...condition) {
        if(table.equals("")) {
            throw new IllegalArgumentException("You must specify a table. Where clause must used after .from() methods");
        }

        for(String c : condition) {
            this.conditions.add(c);
        }

        return this;
    }

    public String build() {
        if(table.equals("")) {
            throw new IllegalArgumentException("You must specify a table.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");

        if(columns.size() > 0) {
            for(int i = 0; i < columns.size(); i++) {
                sb.append(columns.get(i));
                // multiple select column must add ,
                if (i < columns.size() - 1) {
                    sb.append(", ");
                }
            }
        } else {
            sb.append("*");
        }

        // add from table
        sb.append(" FROM ").append(table);

        return sb.toString();
    }
}
