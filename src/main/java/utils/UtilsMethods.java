package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UtilsMethods {

    public static Map<String, String> getSeparatedDateLocation(String dateLocationText){
        Map<String, String> separatedDateLocation = new HashMap<>();
        String[] textParts = dateLocationText.split(" - ");
        if (textParts[1].contains("Dzisiaj")){
            textParts[1] = getCurrentDate();
        }
        else{
            textParts[1] = convertToIsoLocalDate(textParts[1]);
        }
        separatedDateLocation.put("City", textParts[0].trim());
        separatedDateLocation.put("Date", textParts[1].trim());
        return separatedDateLocation;
    }

    public static float getPriceFromText(String priceText){
        String[] textParts = priceText.split(" ");
        return Float.parseFloat(textParts[0].trim().replace(",","."));
        }

    private static String convertToIsoLocalDate(String olxDate){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("pl"));
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String specialFormatCut = "Odświeżono dnia";
        if (olxDate.contains(specialFormatCut)){
            olxDate = olxDate.split(specialFormatCut)[1];
        }
        try {
            LocalDate date = LocalDate.parse(olxDate, inputFormatter);
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format, expected format: dd MMMM yyyy", e);
        }
    }

    public static String getCurrentDate(){
        LocalDate today = LocalDate.now();
        return today.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
    }
