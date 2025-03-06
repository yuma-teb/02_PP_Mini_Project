package query;

import java.util.HashMap;
import java.util.Map;

public class InsertQueryBuilder extends QueryBuilder  {
    private final Map<String, Object> fields = new HashMap<String, Object>();

    public InsertQueryBuilder(TableName table) {
        super(table.toString());
    }

    
    public InsertQueryBuilder setValue(String field, Object value) {
        fields.put(field, value);
        return this;
    }

    @Override
    public String buildQuery() {
        if(fields.isEmpty()) {
            throw new IllegalArgumentException("fields is empty");
        }

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ").append(table).append(" (");
        query.append(String.join(", ", fields.keySet()));
        query.append(") VALUES (");
        query.append(generatePlaceholders(fields.size()));
        query.append(")");

        parameters.addAll(fields.values());

        return query.toString();
    }
}
