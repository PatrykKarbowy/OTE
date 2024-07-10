package utils;

public class SearchItemObject {

    private float price;
    private String title;
    private String link;
    private String date;
    private String city;

    public SearchItemObject(float price, String title, String link, String date, String city) {
        this.price = price;
        this.title = title;
        this.link = link;
        this.date = date;
        this.city = city;
    }

    public float getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

}
