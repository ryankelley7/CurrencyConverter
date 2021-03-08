import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// InputHelper class to handle all user input including:
// Strings, integers, doubles and dual input of String and double
// Each method prints given String parameter as a message initially
public class InputHelper {

    // getString method to get user input of String
    // Prints message if invalid and keeps asking for message if invalid
    // User input String is returned
    protected static String getString(String prompt) {
        Scanner s = new Scanner(System.in);

        String response;
        do {
            System.out.println(prompt);
            response = s.nextLine();

            if ("".equals(response)) {
                response = null;
                System.out.println("Invalid entry - blank entry is not allowed.");
            }
        } while (null == response);

        return response;
    }

    // getInt method to get user input of integer
    // Calls getString method and attempts parsing of String as Integer
    // If parse failed, NumberFormatException caught and asks again for input
    // Returns response integer if correct
    protected static int getInt(String prompt) {

        int response;
        do {
            String str = getString(prompt);
            try {
                response = Integer.parseInt(str);
                return response;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input - integer required");
            }

        } while (true);
    }

    // getDouble method to get user input of double
    // Calls getString method and attemps parsing of String as double
    // If parse failed, NumberFormatException caught and asks again for input
    // Returns response double if correct
    protected static double getDouble(String prompt) {

        double response;
        do {
            String str = getString(prompt);
            try {
                response = Double.parseDouble(str);
                return response;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input - double required");
            }

        } while (true);
    }

    // getCType method to get currency user input, takes in Currency class as 2nd parameter
    // Prints message telling user what currencies are available
    // Calls getString method and attempts to get exchange rate for user input currency
    // If rate cannot be acquired, NullPointerException caught and asks again for input
    // Returns the currency as String if correct
    protected static String getCType(String prompt, Currency exchangeRates) {
        System.out.println("The currencies pnhr converts from/to are " + exchangeRates.CurrencyMap.keySet());
        do {
            String str = getString(prompt).toUpperCase();
            try {
                double rate = exchangeRates.CurrencyMap.get(str);

                return str;
            } catch (NullPointerException e) {
                System.out.println("Invalid input - currency code required");
            }
        } while (true);
    }

    // twoInOne method to get initial currency and amount to convert from
    // User input is read as a String array
    // First should be currency type and second should be amount
    // Attempts acquiring rate for currency and parsing amount as a double
    // Exception thrown if any don't work and asks again for input
    // Returns given user input as String[] if correct
    protected static String[] twoInOne(String prompt, Currency exchangeRates) {
        System.out.println("The currencies pnhr converts from/to are " + exchangeRates.CurrencyMap.keySet());
        do {
            String str = getString(prompt).toUpperCase();
            try {
                String[] words = str.split(" ");
                double rate = exchangeRates.CurrencyMap.get(words[0]);
                double amount = Double.parseDouble(words[1]);
                return words;
            } catch (Exception e) {
                System.out.println("Invalid input - <currency> <amount>");
            }
        } while (true);
    }
}
