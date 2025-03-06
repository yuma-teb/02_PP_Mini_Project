package query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class QueryBuilder {
    protected String table;
    protected List<Object> parameters = new ArrayList<>();

    public QueryBuilder(String table) {
        this.table = table;
    }

    // build query function
    public abstract String buildQuery();



    public List<Object> getParameters() {
        return parameters;
    }

    /**
     * a function to build the "?" for prepare statement
     *
     * @param count: number of the "?" for building in query
     * @return: string that join "?" with ","
     */
    protected String generatePlaceholders(int count) {
        return String.join(", ", Collections.nCopies(count, "?"));
    }
}
