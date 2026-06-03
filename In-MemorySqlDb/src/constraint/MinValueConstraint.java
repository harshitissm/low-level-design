package constraint;

public class MinValueConstraint implements Constraint {

    private final int minValue;

    public MinValueConstraint(int minValue) {
        this.minValue = minValue;
    }

    @Override
    public void validate(Object value) {
        if (!(value instanceof Integer)) {
            throw new RuntimeException("Expected Integer");
        }

        Integer number = (Integer) value;

        if (number < minValue) {
            throw new RuntimeException("Integer below minimum value");
        }
    }
}