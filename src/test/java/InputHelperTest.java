import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InputHelperTest {

    private static final InputStream OG_STDIN = System.in;

    // Simple test to check if integer is entered as user input
    @Test
    public void getInt1() {
        System.setIn(new ByteArrayInputStream("3\n".getBytes()));
        assertEquals(3, InputHelper.getInt("3"), "getInt1 did not return 3");
    }

    // Simple test for 1
    @Test
    public void getInt2() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        assertEquals(1, InputHelper.getInt("1"), "getInt2 did not return 1");
    }

//    @Test
//    public void getIntInvalid() {
//        System.setIn(new ByteArrayInputStream("Nine\n".getBytes()));
//        NumberFormatException e = assertThrows(NumberFormatException.class,
//                () -> InputHelper.getInt("Nine"),
//                "Expected NumberFormatException for non-integer");
////        System.setIn(new ByteArrayInputStream("Nine\n".getBytes()));
//        assertTrue(e.getMessage().contains("Invalid input"));
//    }

    // Simple test to check if input is double for 1
    @Test
    public void getDouble1() {
        System.setIn(new ByteArrayInputStream("1.000\n".getBytes()));
        assertEquals(1.000, InputHelper.getDouble("1.000"), "getDouble1 failed");
    }

    // Test to check if 2.325 is detected and returned as double
    @Test
    public void getDouble2() {
        System.setIn(new ByteArrayInputStream("2.325\n".getBytes()));
        assertEquals(2.325, InputHelper.getDouble("2.325"), "getDouble2 failed");
    }

    // Uppercase tests for each currency
    @Test
    public void getCTypeUppercase1() {
        System.setIn(new ByteArrayInputStream("AUD\n".getBytes()));
        Currency exchangeRates = new Currency();
        assertEquals("AUD", InputHelper.getCType("AUD", exchangeRates));
    }

    @Test
    public void getCTypeUppercase2() {
        System.setIn(new ByteArrayInputStream("GBP\n".getBytes()));
        Currency exchangeRates = new Currency();
        assertEquals("GBP", InputHelper.getCType("GBP", exchangeRates));
    }

    @Test
    public void getCTypeUppercase3() {
        System.setIn(new ByteArrayInputStream("EUR\n".getBytes()));
        Currency exchangeRates = new Currency();
        assertEquals("EUR", InputHelper.getCType("EUR", exchangeRates));
    }

    @Test
    public void getCTypeUppercase4() {
        System.setIn(new ByteArrayInputStream("JPY\n".getBytes()));
        Currency exchangeRates = new Currency();
        assertEquals("JPY", InputHelper.getCType("JPY", exchangeRates));
    }

    @Test
    public void getCTypeUppercase5() {
        System.setIn(new ByteArrayInputStream("USD\n".getBytes()));
        Currency exchangeRates = new Currency();
        assertEquals("USD", InputHelper.getCType("USD", exchangeRates));
    }

    // Lowercase tests for each currency
    @Test
    public void getCTypeLowercase1() {
        System.setIn(new ByteArrayInputStream("aud\n".getBytes()));
        Currency exchangeRates = new Currency();
        assertEquals("AUD", InputHelper.getCType("aud", exchangeRates));
    }

    @Test
    public void getCTypeLowercase2() {
        System.setIn(new ByteArrayInputStream("gbp\n".getBytes()));
        Currency exchangeRates = new Currency();
        assertEquals("GBP", InputHelper.getCType("GBP", exchangeRates));
    }

    @Test
    public void getCTypeLowercase3() {
        System.setIn(new ByteArrayInputStream("eur\n".getBytes()));
        Currency exchangeRates = new Currency();
        assertEquals("EUR", InputHelper.getCType("eur", exchangeRates));
    }

    @Test
    public void getCTypeLowercase4() {
        System.setIn(new ByteArrayInputStream("jpy\n".getBytes()));
        Currency exchangeRates = new Currency();
        assertEquals("JPY", InputHelper.getCType("jpy", exchangeRates));
    }

    @Test
    public void getCTypeLowercase5() {
        System.setIn(new ByteArrayInputStream("usd\n".getBytes()));
        Currency exchangeRates = new Currency();
        assertEquals("USD", InputHelper.getCType("usd", exchangeRates));
    }

    // Standard test, 100 AUD
    @Test
    public void twoInOne1() {
        System.setIn(new ByteArrayInputStream("AUD 100\n".getBytes()));
        Currency exchangeRates = new Currency();
        String[] expected = new String[]{"AUD", "100"};
        assertArrayEquals(expected, InputHelper.twoInOne("AUD 100", exchangeRates));
    }

    // Edge case, large USD
    @Test
    public void twoInOne2() {
        System.setIn(new ByteArrayInputStream("USD 9999.99\n".getBytes()));
        Currency exchangeRates = new Currency();
        String[] expected = new String[]{"USD", "9999.99"};
        assertArrayEquals(expected, InputHelper.twoInOne("USD 9999.99", exchangeRates));
    }

    // Standard test, 500 EUR
    @Test
    public void twoInOne3() {
        System.setIn(new ByteArrayInputStream("EUR 500\n".getBytes()));
        Currency exchangeRates = new Currency();
        String[] expected = new String[]{"EUR", "500"};
        assertArrayEquals(expected, InputHelper.twoInOne("EUR 500", exchangeRates));
    }

    // Edge case, negative JPY
    @Test
    public void twoInOne4() {
        System.setIn(new ByteArrayInputStream("JPY -500\n".getBytes()));
        Currency exchangeRates = new Currency();
        String[] expected = new String[]{"JPY", "-500"};
        assertArrayEquals(expected, InputHelper.twoInOne("JPY -500", exchangeRates));
    }

    // Edge case, lowercase and zero with GBP
    @Test
    public void twoInOne5() {
        System.setIn(new ByteArrayInputStream("gbp 0\n".getBytes()));
        Currency exchangeRates = new Currency();
        String[] expected = new String[]{"GBP", "0"};
        assertArrayEquals(expected, InputHelper.twoInOne("GBP 0", exchangeRates));
    }

    // Restore standard system.in after manually setting during each test run
    @AfterEach
    public void restoreStdin() {
        System.setIn(OG_STDIN);
    }

}