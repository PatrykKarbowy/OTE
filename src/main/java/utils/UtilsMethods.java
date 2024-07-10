package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class UtilsMethods {

    public static Map<String, String> getSeparatedDateLocation(String dateLocationText){
        Map<String, String> separatedDateLocation = new HashMap<>();
        String[] textParts = dateLocationText.split(" - ");
        if (textParts[1].contains("Dzisiaj")){
            LocalDate today = LocalDate.now();
            String dateString = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
            textParts[1] = dateString;
        }
        separatedDateLocation.put("City", textParts[0].trim());
        separatedDateLocation.put("Date", textParts[1].trim());
        return separatedDateLocation;
    }

    public static float getPriceFromText(String priceText){
        String[] textParts = priceText.split(" ");
        return Float.parseFloat(textParts[0].trim().replace(",","."));
        }
    }
