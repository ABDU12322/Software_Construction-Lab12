package expressivo;

/**
 * Immutable binary multiplication expression: (left * right)
 */
public final class MulExpr implements Expression {
    private final Expression left;
    private final Expression right;

    public MulExpr(Expression left, Expression right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }
        this.left = left;
        this.right = right;
        checkRep();
    }

    private void checkRep() {
        assert left != null && right != null;
    }

    @Override
    public String toString() {
        // Fully parenthesized, single space either side of operator as tests expect
        return "(" + left.toString() + " * " + right.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MulExpr)) return false;
        MulExpr other = (MulExpr) o;
        return this.left.equals(other.left) && this.right.equals(other.right);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }
}
