package expressivo;

/**
 * Represents a numeric literal (integer or floating-point).
 */
public final class NumberExpr implements Expression {
    private final double value;

    public NumberExpr(double value) {
        this.value = value;
        checkRep();
    }

    private void checkRep() {
        assert !Double.isNaN(value) : "value must not be NaN";
    }

    @Override
    public String toString() {
        // If this is an integer-valued double, print as "<int>.0" to match tests
        if (value == (long) value) {
            return String.valueOf((long) value) + ".0";
        }
        // Otherwise, use standard double string
        return Double.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NumberExpr)) return false;
        NumberExpr other = (NumberExpr) o;
        return Double.compare(this.value, other.value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
