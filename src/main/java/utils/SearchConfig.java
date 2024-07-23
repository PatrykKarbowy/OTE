package utils;

public class SearchConfig {
    private String searchPhrase;
    private String searchLocation;
    private int saveElements;
    private int priceFrom;
    private int priceTo;

    public SearchConfig(String searchPhrase, String searchLocation, int saveElements, int priceFrom, int priceTo) {
        this.searchPhrase = searchPhrase;
        this.searchLocation = searchLocation;
        this.saveElements = saveElements;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
    }

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public String getSearchLocation() {
        return searchLocation;
    }

    public int getSaveElements() {
        return saveElements;

    }

    public int getPriceFrom() {
        return priceFrom;
    }

    public int getPriceTo() {
        return priceTo;
    }

    @Override
    public String toString() {
        return "SearchConfig{" +
                "searchPhrase = '" + searchPhrase + '\'' +
                ", searchLocation = '" + searchLocation + '\'' +
                ", saveElements = " + saveElements +
                ", priceFrom = " + priceFrom +
                ", priceTo = " + priceTo +
                '}';
    }

}
