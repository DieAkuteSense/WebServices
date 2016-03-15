package resources;

public class ConfigData {
    public double userLat;
    public double userLon;

    public ConfigData(double userLat, double userLon) {
        this.userLat = userLat;
        this.userLon = userLon;
    }

    public double getUserLat() {
        return userLat;
    }

    public void setUserLat(double userLat) {
        this.userLat = userLat;
    }

    public double getUserLon() {
        return userLon;
    }

    public void setUserLon(double userLon) {
        this.userLon = userLon;
    }
}
