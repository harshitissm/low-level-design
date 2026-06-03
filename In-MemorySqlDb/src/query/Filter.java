package query;

import database.Row;

public interface Filter {

    boolean matches(Row row);

    String getColumn();

    Object getValue();
}