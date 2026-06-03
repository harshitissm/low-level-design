package index;

import database.Row;

import java.util.List;

public interface Index {

    void add(Row row);

    void remove(Row row);

    List<Row> search(Object value);
}