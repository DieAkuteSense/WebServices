package geolocation;

public class ResponseData {
    private String input;
    private double lat;
    private double lon;

    public ResponseData() {

    }

    public ResponseData(String input) {
        this.input = input;
        parseInput();
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
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

    private void parseInput() {
        for(int i = 0; i < input.length()-2; i++) {
            if(input.contains("lat")) {
                this.lat = Double.parseDouble(input.substring(i,i+2));
            }
        }
    }
}