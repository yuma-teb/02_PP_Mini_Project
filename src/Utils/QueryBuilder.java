package Utils;

import java.util.*;

public class QueryBuilder {
//    private List<String> tables;
    private String table;

    private Map<String, Object> insertFields = new HashMap<>();
    private Map<String, Object> updateFields = new HashMap<>();

    private List<String> columns;
    private List<String> conditions;

    private List<Object> parameters = new ArrayList<>();

    public QueryBuilder(String table) {
        this.table = table;
        this.columns = new ArrayList<>();
        this.conditions = new ArrayList<String>();
    }

    /**
     * a function which is set the column to query from
     * @param column
     * @return QueryBuilder object
     */
    public QueryBuilder select (String ...column) {
        for(String col : column) {
            this.columns.add(col);
        }
        return this;
    }

    /**
     * a function which is used to received fields to create data
     *
     * @param fields
     * @return QueryBuilder object
     */
    public QueryBuilder insert (Map<String, Object> fields) {
        this.insertFields.putAll(fields);
        return this;
    }

    /**
     * a function which set the field to update
     *
     * @param fields: field to update
     * @return QueryBuilder object
     */
    public QueryBuilder update (Map<String, Object> fields) {
        this.updateFields.putAll(fields);
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

    /**
     * a function which return final string
     * @return
     */
    public String build() {
        if(table.equals("")) {
            throw new IllegalArgumentException("You must specify a table.");
        }

        StringBuilder sb = new StringBuilder();

        if(!insertFields.isEmpty()) {
            // build insert query
            // INSERT INTO table (col1, col2) values (?, ?);
            sb.append("INSERT INTO ").append(table).append(" ("); // * INSERT INTO ( *
            sb.append(String.join(",", insertFields.keySet())); // a, b, c
            sb.append(") VALUES ("); // ) VALUES (
            sb.append(generatePlaceholders(insertFields.size()));
            sb.append(")");

            // get parameters values
            parameters.addAll(insertFields.values());
        }
        else if(!updateFields.isEmpty()) {
            // build update query
            // UPDATE table set
            sb.append("UPDATE ").append(table).append(" SET "); // UPDATE table set

            // create filed to update string
            List<String> updateFields = new ArrayList<>();
            for(String f: updateFields) {
                updateFields.add(f + "= ?");
            }
            sb.append(String.join(",", updateFields));
            parameters.addAll(updateFields);

            // build where query
            if(conditions.isEmpty()) {
                sb.append(" WHERE ");
                sb.append(String.join(" AND ", conditions));
            } else {
                throw new IllegalArgumentException("You must specify at least one condition.");
            }
        } else {
            sb.append("SELECT ");

            // check if there are any sepcify return columns
            if(!columns.isEmpty()) {
               sb.append(String.join(", ", columns));
            } else {
                sb.append("*");
            }

            // add from table
            sb.append(" FROM ").append(table);
        }


        return sb.toString();
    }

    /**
     * a function to build the "?" for prepare statement
     *
     * @param count: number of the "?" for building in query
     * @return: string that join "?" with ","
     */
    private String generatePlaceholders(int count) {
        return String.join(", ", Collections.nCopies(count, "?"));
    }

    /**
     * a function to return list of value to update or insert
     *
     * @return
     */
    public List<Object> getParameters() {
        return parameters;
    }
}
