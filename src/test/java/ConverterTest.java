import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class ConverterTest {
    @Test
    public void initMenu1() {
        Converter test = new Converter();
        assertFalse(test.initMenu(0), "Number of currencies cannot be < 1");
    }

    @Test
    public void initMenu2() {
        Converter test = new Converter();
        assertFalse(test.initMenu(4), "Number of currencies cannot be > 3");
    }

    @Test
    public void initMenu3() {
        Converter test = new Converter();
        assertTrue(test.initMenu(1), "initMenu3 did not return true");
    }

    @Test
    public void initMenu4() {
        Converter test = new Converter();
        assertTrue(test.initMenu(2), "initMenu4 did not return true");
    }

    @Test
    public void initMenu5() {
        Converter test = new Converter();
        assertTrue(test.initMenu(3), "initMenu5 did not return true");
    }

    // Complex total conversion using three currencies, edge cases combined
    // Large decimal + negative + zero
    @Test
    public void conversion1() {
        Converter test = new Converter();
        HashMap<String, Double> testMap = new HashMap<String, Double>();
        testMap.put("AUD", 9999.9999);
        testMap.put("USD", -50.01);
        testMap.put("GBP", 0.00);
        test.setCurrenciesToConvert(testMap);
        List<Double> expectedList = new ArrayList<Double>();
        expectedList.add(9999.9999/1.454*107.770);
        expectedList.add(0.00/0.809*107.770);
        expectedList.add(-50.01/1.000*107.770);
        assertEquals(expectedList, test.conversion("JPY"));
    }

    // Complex total conversion using two currencies, edge cases combined
    // Large + small negative decimal
    @Test
    public void conversion2() {
        Converter test = new Converter();
        HashMap<String, Double> testMap = new HashMap<String, Double>();
        testMap.put("EUR", 9999.99);
        testMap.put("JPY", -0.0001);
        test.setCurrenciesToConvert(testMap);
        List<Double> expectedList = new ArrayList<Double>();
        expectedList.add(-0.0001/107.770*1.454);
        expectedList.add(9999.99/0.905*1.454);
        assertEquals(expectedList, test.conversion("AUD"));
    }

    // Total conversion using one currency
    // Edge small positive decimal
    @Test
    public void conversion3() {
        Converter test = new Converter();
        HashMap<String, Double> testMap = new HashMap<String, Double>();
        testMap.put("GBP", 0.0001);
        test.setCurrenciesToConvert(testMap);
        List<Double> expectedList = new ArrayList<Double>();
        expectedList.add(0.0001/0.809*1.000);
        assertEquals(expectedList, test.conversion("USD"));
    }
}