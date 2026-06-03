package database;

import index.Index;
import index.IndexManager;
import query.EqualFilter;
import query.Filter;
import schema.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Table {

    private final String name;
    private final Schema schema;
    private final List<Row> rows = new ArrayList<>();
    private final IndexManager indexManager = new IndexManager();

    public Table(String name, Schema schema) {
        this.name = name;
        this.schema = schema;
    }

    public void createIndex(String columnName) {
        indexManager.createIndex(columnName);
        Index index = indexManager.getIndex(columnName);
        for (Row row : rows) {
            index.add(row);
        }
    }

    public void insert(Row row) {
        schema.validate(row);
        rows.add(row);
        indexManager.add(row);
    }

    public List<Row> query(List<Filter> filters) {
        List<Row> candidates = null;

        for (Filter filter : filters) {
            if (!(filter instanceof EqualFilter)) {
                continue;
            }

            if (!indexManager.hasIndex(filter.getColumn())) {
                continue;
            }

            candidates = indexManager
                    .getIndex(filter.getColumn())
                    .search(filter.getValue());

            break;
        }

        if (candidates == null) {
            candidates = rows;
        }

        List<Row> result = new ArrayList<>();

        for (Row row : candidates) {
            boolean matched = true;

            for (Filter filter : filters) {
                if (!filter.matches(row)) {
                    matched = false;
                    break;
                }
            }

            if (matched) {
                result.add(row);
            }
        }

        return result;
    }

    public void update(List<Filter> filters, Map<String, Object> updates) {
        List<Row> matchingRows = query(filters);

        for (Row row : matchingRows) {
            indexManager.remove(row);

            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                row.put(entry.getKey(), entry.getValue());
            }

            schema.validate(row);
            indexManager.add(row);
        }
    }

    public void delete(List<Filter> filters) {
        List<Row> matchingRows = query(filters);

        for (Row row : matchingRows) {
            indexManager.remove(row);
            rows.remove(row);
        }
    }

    public void printAll() {
        rows.forEach(System.out::println);
    }
}