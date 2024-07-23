package config;

import utils.UtilsMethods;

public class MainConfig {
    public static final String PAGE_URL = "http://www.olx.pl";
    public static final String FILE_NAME = "OLX_Search_Result_" + UtilsMethods.getCurrentDate();
    public static final String SHEET_NAME = "Search_Result_" + UtilsMethods.getCurrentDate();
    public static final String[] COLUMN_NAMES = {"Price", "Title", "Link", "Date", "City"};
}
