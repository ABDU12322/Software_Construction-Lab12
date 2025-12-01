package expressivo;

/**
 * Represents a variable (letters only).
 */
public final class VariableExpr implements Expression {
    private final String name;

    public VariableExpr(String name) {
        if (name == null || name.isEmpty() || !name.matches("[A-Za-z]+")) {
            throw new IllegalArgumentException("Variable name must be non-empty letters only");
        }
        this.name = name;
        checkRep();
    }

    private void checkRep() {
        assert name != null && !name.isEmpty();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof VariableExpr)) return false;
        VariableExpr other = (VariableExpr) o;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
