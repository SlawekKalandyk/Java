package mainpack;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

public class ATest {

    @Test
    public void checkIfiEquals1ReturnsPierwszy() {
        A a = new A();

        assertEquals(a.met(1), "pierwszy");
    }

    @Test
    public void checkIfiEquals2ReturnsDrugi() {
        A a = new A();

        assertEquals(a.met(2), "drugi");
    }

    @Test
    public void checkIfiOtherThan1Or2ReturnsInny() {
        A a = new A();

        assertEquals(a.met(3), "inny");
    }
}
