import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class Converter {

    // Private instance variables to be initialised in constructor
    // currenciesToConvert hashmap stores the type of currencies to be converted from
    // convertedCurrencies stores a list of resulting conversions to target currency
    // exchangeRates instantiates the Currency class to allow conversions
    // to be calculated with their corresponding rates
    private static HashMap<String, Double> currenciesToConvert;
    private static List<Double> convertedCurrencies;
    public static Currency exchangeRates;

    // Converter constructor, instantiates variables for Converter
    public Converter() {
        this.currenciesToConvert = new HashMap<String, Double>();
        this.convertedCurrencies = new ArrayList<Double>();
        this.exchangeRates = new Currency();
    }

    // initMenu method checks if chosen number of currencies to convert from
    // is outside possible parameters and returns false with a message
    // Returns true if 1, 2 or 3
    protected static boolean initMenu(int selection) {
        if (selection > 3 || selection < 1) {
            System.out.println("Invalid entry - Must enter a number between 1 and 3");
            return false;
        }
        return true;
    }

    // enterCurrency method calls View method to get a list of currencies
    // and amounts to convert from
    // If the View method returns an empty Hashmap, return false
    // Return true if not empty
    public boolean enterCurrency(int selection) {
        this.currenciesToConvert = View.getTypeOfCurrencies(selection);
        if(this.currenciesToConvert.isEmpty()) {
            return false;
        }
        return true;
    }

    // conversion method converts each currency and amount to target currency
    // in order and stores each conversion in a double list
    // Double list is returned
    public static List<Double> conversion(String newCurrency) {
        double totalConversion = 0;
        for (String s : currenciesToConvert.keySet()) {
            String key = s;
            double value = currenciesToConvert.get(s);
            convertedCurrencies.add(exchangeRates.convert(key, value, newCurrency));
        }
        return convertedCurrencies;
    }

    // Setter for Hashmap currenciesToConvert, specifically for testing purposes
    public static void setCurrenciesToConvert(HashMap<String, Double> currenciesToConvert) {
        Converter.currenciesToConvert = currenciesToConvert;
    }

}