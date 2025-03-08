package query;

import java.util.*;


public class UpdateQueryBuilder extends QueryBuilder implements IConditionFunction {
    private final Map<String, Object> fields = new HashMap<>();
    private final List<String> conditions = new ArrayList<>();

    public UpdateQueryBuilder(TableName table) {
        super(table.toString());
    }

    public UpdateQueryBuilder setValue(String field, Object value) {
        fields.put(field, value);
        return this;
    }

    @Override
    public UpdateQueryBuilder where(String field, Object value) {
        conditions.add(field + " = ?"); // ex: name = ?
        parameters.add(value);
        return this;
    }

    @Override
    public String buildQuery() {
        // example:  UPDATE Employee SET name = ?, age = ? WHERE id = ?

        if (fields.isEmpty()) { // update must required field
            throw new IllegalStateException("No fields specified for UPDATE.");
        }
        if (conditions.isEmpty()) { // condition must specify to find row to update
            throw new IllegalStateException("WHERE clause is required for UPDATE queries.");
        }
//        name = ?,
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(table).append(" SET ");

        // create "field = ?"
        List<String> fieldsToSet = new ArrayList<>();
        for(String field: fields.keySet()) {
            fieldsToSet.add(field + " = ?"); //
        }
        query.append(String.join(", ", fieldsToSet));
        parameters.addAll(fields.values());

        // check where condition
        if(!conditions.isEmpty()) {
            query.append(" WHERE ");
            query.append(String.join(" AND ", conditions));
        }

        return query.toString();
    }
}
