package constraint;

public class MaxLengthConstraint implements Constraint {

    private final int maxLength;

    public MaxLengthConstraint(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void validate(Object value) {
        if (!(value instanceof String)) {
            throw new RuntimeException("Expected String");
        }

        String str = (String) value;

        if (str.length() > maxLength) {
            throw new RuntimeException("String length exceeds limit");
        }
    }
}