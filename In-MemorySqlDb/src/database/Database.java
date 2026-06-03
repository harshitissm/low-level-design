package database;

import schema.Schema;

import java.util.HashMap;
import java.util.Map;

public class Database {

    private final Map<String, Table> tables = new HashMap<>();

    public void createTable(String tableName, Schema schema) {
        tables.put(tableName, new Table(tableName,                 schema));
    }

    public Table getTable(String tableName) {
        return tables.get(tableName);
    }

    public void dropTable(String tableName) {
        tables.remove(tableName);
    }
}