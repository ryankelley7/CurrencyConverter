import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CurrencyTest {

    // Simple tests to check if method is returning correct conversion rate
    @Test
    public void TestGetConversionRate1() {
        Currency C = new Currency();
        double rate = C.getConversionRate("USD");
        assertEquals(1.000, rate, "getConversionRate1 did not return expected conversion rate");
    }

    @Test
    public void TestGetConversionRate2() {
        Currency C = new Currency();
        double rate = C.getConversionRate("GBP");
        assertEquals(0.809, rate, "getConversionRate2 did not return expected conversion rate");
    }

    @Test
    public void TestGetConversionRate3() {
        Currency C = new Currency();
        double rate = C.getConversionRate("EUR");
        assertEquals(0.905, rate, "getConversionRate3 did not return expected conversion rate");
    }

    @Test
    public void TestGetConversionRate4() {
        Currency C = new Currency();
        double rate = C.getConversionRate("JPY");
        assertEquals(107.770, rate, "getConversionRate4 did not return expected conversion rate");
    }

    @Test
    public void TestGetConversionRate5() {
        Currency C = new Currency();
        double rate = C.getConversionRate("AUD");
        assertEquals(1.454, rate, "getConversionRate5 did not return expected conversion rate");
    }

    // Standard AUD to USD
    @Test
    public void convert1() {
        Currency test = new Currency();
        double calculation = test.convert("AUD", 1000.000, "USD");
        assertEquals(1000/1.454*1.000, calculation);
    }

    // Large decimal USD to JPY edge case
    @Test
    public void convert2() {
        Currency test = new Currency();
        double calculation = test.convert("USD", 9999999.199999, "JPY");
        assertEquals(9999999.199999*107.770, calculation);
    }

    // Small negative GBP to EUR edge case
    @Test
    public void convert3() {
        Currency test = new Currency();
        double calculation = test.convert("GBP", -0.01, "EUR");
        assertEquals(-0.01/0.809*0.905, calculation);
    }
}