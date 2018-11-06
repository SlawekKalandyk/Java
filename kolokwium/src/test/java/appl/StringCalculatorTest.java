package appl;

import static org.junit.Assert.*;

import excp.TooBigNumberException;
import org.junit.Test;

public class StringCalculatorTest {
    @Test
    public void stringAdditionTest() {
        StringCalculator calc = new StringCalculator("abc");
        calc.Add("def");

        assertEquals(calc.getResult(),"abcdef");
    }

    @Test
    public void stringMultiplyingTest() {
        StringCalculator calc = new StringCalculator("abc");

        try {
            calc.Multiply(3);
        } catch(TooBigNumberException t) {
            t.printStackTrace();
        }

        assertEquals(calc.getResult(), "abcabcabc");
    }

    @Test
    public void tooBigNumberWhenMultiplyingThrowsExceptionTest() {
        StringCalculator calc = new StringCalculator("abc");

        try {
            calc.Multiply(6);
        } catch(TooBigNumberException t) {
            t.printStackTrace();
        }

    }
}
