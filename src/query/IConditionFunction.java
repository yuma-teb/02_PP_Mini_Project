package query;

public interface IConditionFunction {

    /**
     * a function which is used to set where condition
     *
     * select query
     * update query
     *
     * @param filed
     * @param value
     * @return
     */
    QueryBuilder where(String filed, Object value);
}
