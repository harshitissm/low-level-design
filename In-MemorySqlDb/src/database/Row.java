package database;

import java.util.HashMap;
import java.util.Map;

public class Row {

    private final Map<String, Object> values = new HashMap<>();

    public void put(String column, Object value) {
        values.put(column, value);
    }

    public Object getValue(String column) {
        return values.get(column);
    }

    public Map<String, Object> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return values.toString();
    }
}