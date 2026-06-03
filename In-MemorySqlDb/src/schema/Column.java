package schema;

import constraint.Constraint;

import java.util.List;

public class Column {

    private final String name;
    private final DataType dataType;

    private final List<Constraint> constraints;

    public Column(String name, DataType dataType, List<Constraint> constraints) {
        this.name = name;
        this.dataType = dataType;
        this.constraints = constraints;
    }

    public String getName() {
        return name;
    }

    public DataType getDataType() {
        return dataType;
    }

    public List<Constraint> getConstraints() {
        return constraints;
    }
}