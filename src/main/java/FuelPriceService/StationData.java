package FuelPriceService;

/**
 * Created by Olli on 09.03.2016.
 */
public class StationData {
    private String name;
    private double lat;
    private double lon;
    private String brand;
    private double dist;
    private double price;
    private String stationID;
    private String street;
    private String houseNumber;
    private int postCode;
    private String place;
    private boolean isOpen;

    public StationData(String name, double lat, double lon, String brand, double dist, double price, String id, String street, String houseNumber, int postCode, String place, boolean isOpen) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.brand = brand;
        this.dist = dist;
        this.price = price;
        this.stationID = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postCode = postCode;
        this.place = place;
        this.isOpen = isOpen;
    }

    public StationData() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return String.format("FuelData: Station ID = %s, Price = %s", stationID, price);
    }
}
