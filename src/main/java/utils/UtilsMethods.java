package utils;

import java.util.HashMap;
import java.util.Map;

public class UtilsMethods {

    public static Map<String, String> getSeparatedDateLocation(String dateLocationText){
        Map<String, String> separatedDateLocation = new HashMap<>();
        String[] textParts = dateLocationText.split(" - ");
        if (textParts.length == 2) {
            separatedDateLocation.put("City", textParts[0].trim());
            separatedDateLocation.put("Date", textParts[1].trim());
        } else {
            throw new IllegalArgumentException("Input string is not in the expected format 'City - Date'.");
        }
        return separatedDateLocation;
    }

    public static float getPriceFromText(String priceText){
        String[] textParts = priceText.split(" ");
        return Float.parseFloat(textParts[0].trim().replace(",","."));
        }
    }
