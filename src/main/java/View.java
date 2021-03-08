import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class View {

    // Asks user to input number of currencies to convert from 1-3
    // Returns this number
    public static int getNumberOfCurrencies() {
        int selection = InputHelper.getInt("How many currencies would you like to convert today?");
        return selection;
    }

    // getTypeOfCurrencies is called by Converter method.
    // Asks for user input twice in succession: First currency type then amount
    // Returns a hashmap containing currency String as keys and amount double as values
    // If the same currency is entered as previously, message is printed and hashmap cleared
    public static HashMap<String, Double> getTypeOfCurrencies(int selection) {
        HashMap<String, Double> currenciesToConvert = new HashMap<String, Double>();
        for (int i = selection; i>0; i--) {
            String[] response = InputHelper.twoInOne("Please enter the currency and amount you would like to exchange, in the format <currency> <amount>:", Converter.exchangeRates);
            String CInput = response[0];
            double DInput = Double.parseDouble(response[1]);
            currenciesToConvert.put(CInput, DInput);
        }
        if (currenciesToConvert.size() != selection) {
            System.out.println("You need to enter unique currencies.");
            currenciesToConvert.clear();
        }
        return currenciesToConvert;
    }

    // getType method asks user for target currency to convert to
    // Returns the target currency as String
    public static String getTypeOfConversion() {
        String newCurrency = InputHelper.getCType("Please enter the currency you would like to convert to",
                Converter.exchangeRates);
        return newCurrency;
    }

    // getConvertedList calls Converter method to convert all given currencies to target currency
    // They are all converted into a double list and each value is summed together
    // The sum is printed and returned
    public static double getConvertedList(String newCurrency) {
        List<Double> convertedCurrencies = new ArrayList<Double>();
        convertedCurrencies = Converter.conversion(newCurrency);
        double totalConversion = 0;
        for (double d: convertedCurrencies) {
            totalConversion += d;
        }
        System.out.printf("Your currencies have been converted to %s %s%n", newCurrency, String.format("%.2f",
                totalConversion));
        return totalConversion;
    }

    // Main method to test functionality, using implemented methods
    public static void main(String[] args) {
        System.out.println("\nHello, welcome to pnhr's currency converter!");
        int selection = getNumberOfCurrencies();
        Converter program = new Converter();
        while(!program.initMenu(selection)) {
            selection = getNumberOfCurrencies();
        }
        while(!program.enterCurrency(selection)) {
            getTypeOfCurrencies(selection);
        }
        String newCurrency = getTypeOfConversion();
        getConvertedList(newCurrency);
    }
}
