package expressivo;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    /*
     * Testing strategy:
     *
     * toString():
     *   - numbers, variables, add, multiply
     *   - nested expressions
     *   - round-trip check e.equals(parse(e.toString()))
     *
     * equals():
     *   - reflexive, symmetric, transitive
     *   - numeric equality across formats (1 == 1.0 == 1.000)
     *   - variable case sensitivity
     *   - structural equality (order & grouping)
     *
     * hashCode():
     *   - equal expressions have equal hashcodes
     */

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }


    @Test
    public void testNumberToString() {
        Expression e1 = new NumberExpr(5);
        Expression e2 = new NumberExpr(3.14);

        assertEquals("5.0", e1.toString());
        assertEquals("3.14", e2.toString());
    }

    @Test
    public void testVariableToString() {
        Expression e = new VariableExpr("x");
        assertEquals("x", e.toString());

        Expression e2 = new VariableExpr("abc");
        assertEquals("abc", e2.toString());
    }

    @Test
    public void testAddToString() {
        Expression e = new AddExpr(new NumberExpr(1), new VariableExpr("x"));
        assertEquals("(1.0 + x)", e.toString());
    }

    @Test
    public void testMultiplyToString() {
        Expression e = new MulExpr(new NumberExpr(2), new VariableExpr("y"));
        assertEquals("(2.0 * y)", e.toString());
    }

    @Test
    public void testNestedToString() {
        Expression e = new AddExpr(
                new MulExpr(new NumberExpr(2), new VariableExpr("x")),
                new NumberExpr(3)
        );
        assertEquals("((2.0 * x) + 3.0)", e.toString());
    }


    @Test
    public void testEqualsNumber() {
        Expression n1 = new NumberExpr(1);
        Expression n2 = new NumberExpr(1.0);
        Expression n3 = new NumberExpr(1.000);

        assertEquals(n1, n2);
        assertEquals(n2, n3);
        assertEquals(n1, n3);
    }

    @Test
    public void testNotEqualsNumber() {
        Expression n1 = new NumberExpr(2);
        Expression n2 = new NumberExpr(3);

        assertNotEquals(n1, n2);
    }

    @Test
    public void testEqualsVariable() {
        Expression v1 = new VariableExpr("x");
        Expression v2 = new VariableExpr("x");

        assertEquals(v1, v2);
    }

    @Test
    public void testNotEqualsVariable() {
        Expression v1 = new VariableExpr("x");
        Expression v2 = new VariableExpr("y");

        assertNotEquals(v1, v2);
    }

    @Test
    public void testEqualsAddExpression() {
        Expression e1 = new AddExpr(new NumberExpr(1), new VariableExpr("x"));
        Expression e2 = new AddExpr(new NumberExpr(1.0), new VariableExpr("x"));

        assertEquals(e1, e2);
    }

    @Test
    public void testNotEqualsAddDifferentOrder() {
        Expression e1 = new AddExpr(new NumberExpr(1), new VariableExpr("x"));
        Expression e2 = new AddExpr(new VariableExpr("x"), new NumberExpr(1));

        assertNotEquals(e1, e2);
    }

    @Test
    public void testEqualsMultiplyExpression() {
        Expression e1 = new MulExpr(new NumberExpr(2), new VariableExpr("y"));
        Expression e2 = new MulExpr(new NumberExpr(2.0), new VariableExpr("y"));

        assertEquals(e1, e2);
    }

    @Test
    public void testAddAssociativity() {
        Expression a = new NumberExpr(1);
        Expression b = new NumberExpr(2);
        Expression c = new NumberExpr(3);

       
        Expression expr1 = new AddExpr(a, new AddExpr(b, c));

        
        Expression expr2 = new AddExpr(new AddExpr(a, b), c);

        
        assertNotEquals(expr1, expr2);

        
        double val1 = 1 + (2 + 3);
        double val2 = (1 + 2) + 3;

        assertEquals(val1, val2, 0.00001);
    }

    @Test
    public void testMulAssociativity() {
        Expression a = new NumberExpr(2);
        Expression b = new NumberExpr(3);
        Expression c = new NumberExpr(4);

        
        Expression expr1 = new MulExpr(a, new MulExpr(b, c));

        
        Expression expr2 = new MulExpr(new MulExpr(a, b), c);

        
        assertNotEquals(expr1, expr2);

        
        double val1 = 2 * (3 * 4);
        double val2 = (2 * 3) * 4;

        assertEquals(val1, val2, 0.00001);
    }

    @Test
    public void testNotEqualsDifferentStructure() {
        Expression e1 = new AddExpr(
                new AddExpr(new NumberExpr(1), new NumberExpr(2)),
                new NumberExpr(3)
        );

        Expression e2 = new AddExpr(
                new NumberExpr(1),
                new AddExpr(new NumberExpr(2), new NumberExpr(3))
        );

        assertNotEquals(e1, e2);
    }

    @Test
    public void testHashCodeEqualForEqualExpressions() {
        Expression e1 = new AddExpr(new NumberExpr(1), new VariableExpr("x"));
        Expression e2 = new AddExpr(new NumberExpr(1.0), new VariableExpr("x"));

        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    public void testHashCodeDifferentForDifferentExpressions() {
        Expression e1 = new AddExpr(new NumberExpr(1), new VariableExpr("x"));
        Expression e2 = new AddExpr(new NumberExpr(2), new VariableExpr("x"));

        assertNotEquals(e1.hashCode(), e2.hashCode());
    }
}
