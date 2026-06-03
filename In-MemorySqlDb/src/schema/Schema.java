package schema;

import constraint.Constraint;
import database.Row;

import java.util.List;

public class Schema {

    private final List<Column> columns;

    public Schema(List<Column> columns) {
        this.columns = columns;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void validate(Row row) {
        for (Column column : columns) {

            Object value = row.getValue(column.getName());

            if (value == null) {
                throw new RuntimeException("Null value not allowed");
            }

            validateType(column, value);

            for (Constraint constraint : column.getConstraints()) {
                constraint.validate(value);
            }
        }
    }

    private void validateType(Column column, Object value) {
        if (column.getDataType() == DataType.STRING && !(value instanceof String)) {
            throw new RuntimeException("Type mismatch");
        }

        if (column.getDataType() == DataType.INTEGER && !(value instanceof Integer)) {
            throw new RuntimeException("Type mismatch");
        }
    }
}