import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
 
import static org.junit.jupiter.api.Assertions.*;
 
/**
* Unit tests for the Calculator class.
* Provides coverage for basic arithmetic, mathematical edge cases, 
* and input validation.
*/
@DisplayName("Calculator")
class CalculatorTest {
 
    private Calculator calc;
 
    /**
     * Sets up a fresh Calculator instance before each individual test.
     */
    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }
 
    /**
     * Validates the addition of two positive integers.
     */
    @Test
    @DisplayName("3 + 4 equals 7")
    void testAdd() {
        assertEquals(7.0, calc.add(3, 4), 1e-9);
    }
 
    /**
     * Validates the subtraction of two positive integers.
     */
    @Test
    @DisplayName("10 - 3 equals 7")
    void testSubtract() {
        assertEquals(7.0, calc.subtract(10, 3), 1e-9);
    }
 
    /**
     * Validates the multiplication of two positive integers.
     */
    @Test
    @DisplayName("3 times 4 equals 12")
    void testMultiply() {
        assertEquals(12.0, calc.multiply(3, 4), 1e-9);
    }
 
    /**
     * Validates division resulting in a decimal quotient.
     */
    @Test
    @DisplayName("10 divided by 4 equals 2.5")
    void testDivide() {
        assertEquals(2.5, calc.divide(10, 4), 1e-9);
    }
 
    /**
     * Validates the power function with a positive base and exponent.
     */
    @Test
    @DisplayName("2 to the power of 8 equals 256")
    void testPower() {
        assertEquals(256.0, calc.power(2, 8), 1e-9);
    }
 
    /**
     * Validates the square root of a perfect square.
     */
    @Test
    @DisplayName("square root of 9 equals 3")
    void testSqrt() {
        assertEquals(3.0, calc.sqrt(9), 1e-9);
    }
 
    /**
     * Tests involving negative numbers and boundary values.
     */
    @Nested
    @DisplayName("edge cases")
    class EdgeCases {
 
        /**
         * Tests addition with various combinations of negative operands.
         * * @param a The first operand
         * @param b The second operand
         * @param expected The expected sum
         */
        @ParameterizedTest(name = "[{index}]")
        @DisplayName("add handles negative operands")
        @CsvSource({
            "-3, 4, 1",
            "3, -4, -1",
            "-3, -4, -7"
        })
        void addNegatives(double a, double b, double expected) {
            assertEquals(expected, calc.add(a, b), 1e-9);
        }
 
        /**
         * Tests subtraction with various combinations of negative operands.
         * * @param a The minuend
         * @param b The subtrahend
         * @param expected The expected difference
         */
        @ParameterizedTest(name = "[{index}]")
        @DisplayName("subtract handles negative operands")
        @CsvSource({
            "-3, 4, -7",
            "3, -4, 7",
            "-3, -4, 1"
        })
        void subtractNegatives(double a, double b, double expected) {
            assertEquals(expected, calc.subtract(a, b), 1e-9);
        }
 
        /**
         * Tests multiplication with various combinations of negative operands.
         * * @param a The first factor
         * @param b The second factor
         * @param expected The expected product
         */
        @ParameterizedTest(name = "[{index}]")
        @DisplayName("multiply handles negative operands")
        @CsvSource({
            "-3, 4, -12",
            "3, -4, -12",
            "-3, -4, 12"
        })
        void multiplyNegatives(double a, double b, double expected) {
            assertEquals(expected, calc.multiply(a, b), 1e-9);
        }
 
        /**
         * Tests division with various combinations of negative operands.
         * * @param a The dividend
         * @param b The divisor
         * @param expected The expected quotient
         */
        @ParameterizedTest(name = "[{index}]")
        @DisplayName("divide handles negative operands")
        @CsvSource({
            "-10, 2, -5",
            "10, -2, -5",
            "-10, -2, 5"
        })
        void divideNegatives(double a, double b, double expected) {
            assertEquals(expected, calc.divide(a, b), 1e-9);
        }
 
        /**
         * Verifies that any number multiplied by zero equals zero.
         * * @param a The first factor
         * @param b The second factor
         */
        @ParameterizedTest(name = "[{index}]")
        @DisplayName("multiplying by zero always returns zero")
        @CsvSource({
            "0, 5",
            "5, 0",
            "-5, 0",
            "0, 0"
        })
        void multiplyByZero(double a, double b) {
            assertEquals(0.0, calc.multiply(a, b), 1e-9);
        }
 
        /**
         * Verifies that adding a number to its negative counterpart results in zero.
         */
        @Test
        @DisplayName("a + (-a) always equals zero")
        void additiveInverse() {
            assertAll(
                () -> assertEquals(0.0, calc.add(5,    -5),    1e-9),
                () -> assertEquals(0.0, calc.add(-5,   5),     1e-9),
                () -> assertEquals(0.0, calc.add(3.14, -3.14), 1e-9),
                () -> assertEquals(0.0, calc.add(0,    0),     1e-9)
            );
        }
 
        /**
         * Verifies that any base raised to the power of zero equals one.
         */
        @Test
        @DisplayName("any base to the power of 0 equals 1")
        void powerOfZero() {
            assertAll(
                () -> assertEquals(1.0, calc.power(2,    0), 1e-9),
                () -> assertEquals(1.0, calc.power(-2,   0), 1e-9),
                () -> assertEquals(1.0, calc.power(3.14, 0), 1e-9),
                () -> assertEquals(1.0, calc.power(0,    0), 1e-9)
            );
        }
 
        /**
         * Verifies the square root of zero.
         */
        @Test
        @DisplayName("square root of 0 is 0")
        void sqrtZero() {
            assertEquals(0.0, calc.sqrt(0), 1e-9);
        }
 
        /**
         * Verifies the square root of one.
         */
        @Test
        @DisplayName("square root of 1 is 1")
        void sqrtOne() {
            assertEquals(1.0, calc.sqrt(1), 1e-9);
        }
    }
 
    /**
     * Tests to ensure proper exception handling for illegal inputs.
     */
    @Nested
    @DisplayName("input validation")
    class Validation {
 
        /**
         * Verifies that dividing by zero throws the expected exception and message.
         */
        @Test
        @DisplayName("dividing by zero throws ArithmeticException")
        void divideByZeroThrows() {
            ArithmeticException ex = assertThrows(
                ArithmeticException.class,
                () -> calc.divide(10, 0)
            );
            assertTrue(ex.getMessage().toLowerCase().contains("zero"),
                "Exception message should contain 'zero'");
        }
 
        /**
         * Verifies that square roots of negative numbers throw IllegalArgumentException.
         * * @param n The negative input value
         */
        @ParameterizedTest(name = "[{index}]")
        @DisplayName("sqrt of negative number throws \u2026")
        @ValueSource(doubles = {-1.0, -0.001, Double.NEGATIVE_INFINITY})
        void sqrtNegativeThrows(double n) {
            assertThrows(IllegalArgumentException.class, () -> calc.sqrt(n));
        }
 
        /**
         * Verifies that negative exponents throw IllegalArgumentException.
         * * @param exp The negative exponent value
         */
        @ParameterizedTest(name = "[{index}]")
        @DisplayName("negative exponent throws \u2026")
        @ValueSource(ints = {-1, -5, -100})
        void negativeExponentThrows(int exp) {
            assertThrows(IllegalArgumentException.class, () -> calc.power(2, exp));
        }
 
        /**
         * Performs a sequence of operations to ensure cumulative state behavior is correct.
         */
        @Test
        @DisplayName("full calculation chain produces correct results")
        void fullCalculationChain() {
            assertAll("calculation chain",
                () -> assertEquals(10.0, calc.add(3, 7),       1e-9),
                () -> assertEquals(4.0,  calc.subtract(10, 6), 1e-9),
                () -> assertEquals(20.0, calc.multiply(4, 5),  1e-9),
                () -> assertEquals(4.0,  calc.divide(20, 5),   1e-9),
                () -> assertEquals(16.0, calc.power(4, 2),     1e-9),
                () -> assertEquals(4.0,  calc.sqrt(16),        1e-9)
            );
        }
    }
}