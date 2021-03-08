import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ViewTest {

    private static final InputStream OG_STDIN = System.in;

    // Simple test for correct internal InputHelper call
    @Test
    public void getNumberOfCurrencies() {
        System.setIn(new ByteArrayInputStream("3\n".getBytes()));
        assertEquals(3, View.getNumberOfCurrencies(), "getNumberOfCurrencies did not return correct integer");
    }

    // getTypeOfCurrencies test whether correct starting currency and amount is stored
    // Simulates user input
    // Test for JPY type
    @Test
    public void getTypeOfCurrencies1() {
        Converter test = new Converter();
        Map<String, Double> testMap = new HashMap<String, Double>();
        testMap.put("JPY", 1000.000);
        System.setIn(new ByteArrayInputStream("JPY 1000\n".getBytes()));
        assertEquals(testMap, View.getTypeOfCurrencies(1), "getTypeOfCurrencies did not return correct HashMap");
    }

    // Test for AUD type
    @Test
    public void getTypeOfCurrencies2() {
        Converter test = new Converter();
        Map<String, Double> testMap = new HashMap<String, Double>();
        testMap.put("AUD", 1000.000);
        System.setIn(new ByteArrayInputStream("AUD 1000\n".getBytes()));
        assertEquals(testMap, View.getTypeOfCurrencies(1), "getTypeOfCurrencies did not return correct HashMap");
    }

    // Test for EUR type
    @Test
    public void getTypeOfCurrencies3() {
        Converter test = new Converter();
        Map<String, Double> testMap = new HashMap<String, Double>();
        testMap.put("EUR", 50.000);
        System.setIn(new ByteArrayInputStream("EUR 50\n".getBytes()));
        assertEquals(testMap, View.getTypeOfCurrencies(1), "getTypeOfCurrencies did not return correct HashMap");
    }

    // Edge case for decimal amount in USD
    @Test
    public void getTypeOfCurrencies4() {
        Converter test = new Converter();
        Map<String, Double> testMap = new HashMap<String, Double>();
        testMap.put("USD", 5.500);
        System.setIn(new ByteArrayInputStream("USD 5.5\n".getBytes()));
        assertEquals(testMap, View.getTypeOfCurrencies(1), "getTypeOfCurrencies did not return correct HashMap");
    }

    // Edge case for extremely large, two decimal point amount in JPY
    @Test
    public void getTypeOfCurrencies5() {
        Converter test = new Converter();
        Map<String, Double> testMap = new HashMap<String, Double>();
        testMap.put("JPY", 9999999999.99);
        System.setIn(new ByteArrayInputStream("JPY 9999999999.99\n".getBytes()));
        assertEquals(testMap, View.getTypeOfCurrencies(1), "getTypeOfCurrencies did not return correct HashMap");
    }

    // Edge case for amount of zero in JPY
    @Test
    public void getTypeOfCurrencies6() {
        Converter test = new Converter();
        Map<String, Double> testMap = new HashMap<String, Double>();
        testMap.put("JPY", 0.00);
        System.setIn(new ByteArrayInputStream("JPY 0\n".getBytes()));
        assertEquals(testMap, View.getTypeOfCurrencies(1), "getTypeOfCurrencies did not return correct HashMap");
    }

    // Test correct storage of currencies in uppercase
    @Test
    public void getTypeOfConversionUppercase() {
        Converter test = new Converter();
        System.setIn(new ByteArrayInputStream("AUD\n".getBytes()));
        assertEquals("AUD", View.getTypeOfConversion(), "getTypeOfConversion did not return expected String");
        System.setIn(new ByteArrayInputStream("USD\n".getBytes()));
        assertEquals("USD", View.getTypeOfConversion(), "getTypeOfConversion did not return expected String");
        System.setIn(new ByteArrayInputStream("EUR\n".getBytes()));
        assertEquals("EUR", View.getTypeOfConversion(), "getTypeOfConversion did not return expected String");
        System.setIn(new ByteArrayInputStream("GBP\n".getBytes()));
        assertEquals("GBP", View.getTypeOfConversion(), "getTypeOfConversion did not return expected String");
        System.setIn(new ByteArrayInputStream("JPY\n".getBytes()));
        assertEquals("JPY", View.getTypeOfConversion(), "getTypeOfConversion did not return expected String");
    }

    // Test correct storage of currencies given in lowercase as uppercase
    @Test
    public void getTypeOfConversionLowercase() {
        Converter test = new Converter();
        System.setIn(new ByteArrayInputStream("aud\n".getBytes()));
        assertEquals("AUD", View.getTypeOfConversion(), "getTypeOfConversion did not return expected String");
        System.setIn(new ByteArrayInputStream("usd\n".getBytes()));
        assertEquals("USD", View.getTypeOfConversion(), "getTypeOfConversion did not return expected String");
        System.setIn(new ByteArrayInputStream("eur\n".getBytes()));
        assertEquals("EUR", View.getTypeOfConversion(), "getTypeOfConversion did not return expected String");
        System.setIn(new ByteArrayInputStream("gbp\n".getBytes()));
        assertEquals("GBP", View.getTypeOfConversion(), "getTypeOfConversion did not return expected String");
        System.setIn(new ByteArrayInputStream("jpy\n".getBytes()));
        assertEquals("JPY", View.getTypeOfConversion(), "getTypeOfConversion did not return expected String");
    }

    // Complex system test, using all methods
    @Test
    public void complexTest1() {
        Converter test = new Converter();
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        assertEquals(1, View.getNumberOfCurrencies(), "getNumberOfCurrencies did not return expected value");
        assertTrue(test.initMenu(1), "initMenu did not return true");
        System.setIn(new ByteArrayInputStream("AUD 20".getBytes()));
        assertTrue(test.enterCurrency(1), "enterCurrency did not return true");
        System.setIn(new ByteArrayInputStream("USD\n".getBytes()));
        String newCurrency = View.getTypeOfConversion();
        assertEquals("USD", newCurrency, "newCurrency has unexpected value");
        double totalConversion = View.getConvertedList(newCurrency);
        assertEquals(20/1.454*1.000, totalConversion);
    }

    // Restore standard system.in after manually setting during each test run
    @AfterEach
    public void restoreStdin() {
        System.setIn(OG_STDIN);
    }


}