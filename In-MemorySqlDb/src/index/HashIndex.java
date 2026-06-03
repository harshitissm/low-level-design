package index;

import database.Row;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashIndex implements Index {

    private final String columnName;
    private final Map<Object, Set<Row>> index = new HashMap<>();

    public HashIndex(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public void add(Row row) {
        Object value = row.getValue(columnName);
        index.computeIfAbsent(value, k -> new HashSet<>()).add(row);
    }

    @Override
    public void remove(Row row) {
        Object value = row.getValue(columnName);
        Set<Row> rows = index.get(value);

        if (rows == null) {
            return;
        }

        rows.remove(row);

        if (rows.isEmpty()) {
            index.remove(value);
        }
    }

    @Override
    public List<Row> search(Object value) {
        return new ArrayList<>(index.getOrDefault(value, Collections.emptySet()));
    }
}