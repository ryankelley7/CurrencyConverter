import java.lang.String;
import java.util.HashMap;
import java.util.Map;

public class Currency {

    // Initialised empty map of currencies
    public static Map<String, Double> CurrencyMap = new HashMap<>();

    // Currency constructor places all possible 5 currencies inside CurrencyMap
    public Currency() {
        CurrencyMap.put("USD", 1.000);
        CurrencyMap.put("GBP", 0.809);
        CurrencyMap.put("EUR", 0.905);
        CurrencyMap.put("JPY", 107.770);
        CurrencyMap.put("AUD", 1.454);
    }

    // getConversionRate method returns conversion rate for currency type parameter
    public double getConversionRate(String currencyName) {
        return CurrencyMap.get(currencyName);
    }

    // convert method converts given currency and amount to target currency
    // Returns result as a double
    public static Double convert(String convertFrom, Double amount, String convertTo) {
        if (convertFrom.equals("USD")) {
            return amount * CurrencyMap.get(convertTo);
        }
        else {
            return (amount / CurrencyMap.get(convertFrom)) * CurrencyMap.get(convertTo);
        }
        // Do we need rounding?
    }
}
