package query;

import database.Row;

import java.util.Objects;

public class NotEqualFilter implements Filter {

    private final String column;
    private final Object value;

    public NotEqualFilter(String column, Object value) {
        this.column = column;
        this.value = value;
    }

    @Override
    public boolean matches(Row row) {
        return !Objects.equals(row.getValue(column), value);
    }

    @Override
    public String getColumn() {
        return column;
    }

    @Override
    public Object getValue() {
        return value;
    }
}