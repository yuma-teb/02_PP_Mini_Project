package query;

import java.util.ArrayList;
import java.util.List;


public class SelectQueryBuilder extends QueryBuilder implements IConditionFunction{
    private List<String> conditions = new ArrayList<>();
    private List<String> columns = new ArrayList<>();

    public SelectQueryBuilder(TableName table) {
        super(table.toString());
    }

    @Override
    public SelectQueryBuilder where(String field, Object value) {
        conditions.add(field + " = " + value);
        parameters.add(value);
        return this;
    }

    public SelectQueryBuilder where(String field, Object value, QueryOperator operator) {
        conditions.add(field + " " + operator.toString() + " " + "'" + value + "'");
        parameters.add(value);
        return this;
    }

    @Override
    public String buildQuery() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");

        // columns to query
        if(columns.isEmpty()) {
            query.append("*"); // select all
        } else {
            query.append(String.join(", ", columns));
        }

        query.append(" FROM ").append(table);

        if (!conditions.isEmpty()) {
            query.append(" WHERE ");
            query.append(String.join(" ", conditions));
        }

        return query.toString();
    }
}
