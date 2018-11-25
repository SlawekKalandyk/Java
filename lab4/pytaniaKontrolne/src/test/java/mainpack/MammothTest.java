package mainpack;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

public class MammothTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testIfMeatAsInstanceOfFoodThrowsInadequateFoodException()
    throws InadequateFoodException {
        thrown.expect(InadequateFoodException.class);
        thrown.expectMessage("I don't like meat");
        Mammoth m = new Mammoth();
        m.eat(new Meat());
    }
}
