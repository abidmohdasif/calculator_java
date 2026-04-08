/**
 * Calculator — Phase 1
 *
 * A simple arithmetic calculator with input validation.
 * All methods are stateless; create one instance and reuse it freely.
 */
public class Calculator {

    /**
     * Adds two doubles.
     *
     * @param a first operand
     * @param b second operand
     * @return a + b
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Subtracts b from a.
     *
     * @param a minuend
     * @param b subtrahend
     * @return a − b
     */
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Multiplies two doubles.
     *
     * @param a first factor
     * @param b second factor
     * @return a × b
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Divides a by b.
     *
     * @param a dividend
     * @param b divisor — must not be zero
     * @return a ÷ b
     * @throws ArithmeticException if b is 0
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    /**
     * Raises base to a non-negative integer exponent.
     *
     * @param base the base value
     * @param exp  the exponent — must be ≥ 0
     * @return base ^ exp
     * @throws IllegalArgumentException if exp is negative
     */
    public double power(double base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException(
                    "Exponent must be non-negative, got: " + exp);
        }
        return Math.pow(base, exp);
    }

    /**
     * Returns the square root of n.
     *
     * @param n radicand — must be ≥ 0
     * @return √n
     * @throws IllegalArgumentException if n is negative
     */
    public double sqrt(double n) {
        if (n < 0) {
            throw new IllegalArgumentException(
                    "Cannot take square root of a negative number, got: " + n);
        }
        return Math.sqrt(n);
    }
}