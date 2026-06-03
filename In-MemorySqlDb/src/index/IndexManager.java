package index;

import database.Row;

import java.util.HashMap;
import java.util.Map;

public class IndexManager {

    private final Map<String, Index> indexes = new HashMap<>();

    public void createIndex(String columnName) {
        indexes.put(columnName, new HashIndex(columnName));
    }

    public Index getIndex(String columnName) {
        return indexes.get(columnName);
    }

    public boolean hasIndex(String columnName) {
        return indexes.containsKey(columnName);
    }

    public void add(Row row) {
        indexes.values().forEach(index -> index.add(row));
    }

    public void remove(Row row) {
        indexes.values().forEach(index -> index.remove(row));
    }
}