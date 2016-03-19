package geolocation;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * BACKEND
 * Requests the users current location by locating the devices IP
 * on mobile devices the accuracy could be optimized (not implemented in this application)
 */
@WebService
public class GeoLocation {
    public static final String GEOLOCATION_API_URL = "https://www.googleapis.com/geolocation/v1/geolocate";
    public static final String GEOLOCATION_API_KEY = "AIzaSyDEhCTh03VykiRtsic2ii6GMmFL6Q6ezvY"; // get your own key under https://developers.google.com/maps/documentation/geolocation/get-api-key?hl=de
    public double lat;
    public double lon;

    public GeoLocation() {
    }

    /**
     * prepares and requests the useres current location by using the Google Geolocation API
     * @return Double array {latitude, longitude}
     */
    @Path("/geoLocation")
    @POST
    public Double[] requestLocation() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        ResponseData result = new ResponseData();
        HttpResponse response;
        Double[] coordinates = new Double[2];
        StringBuilder sb = new StringBuilder();
        try {
            // Creating HTTP Post Request to Google Geolocation API //
            HttpPost request = new HttpPost(GEOLOCATION_API_URL + "?key=" + GEOLOCATION_API_KEY);
            StringEntity params = new StringEntity("{\"considerIp\": \"true\"}");
            request.addHeader("content-type", "application/json");
            request.setEntity(params);

            // Get response from Google Geolocation API and parse it to Json Object
            response = httpClient.execute(request);
            String temp = EntityUtils.toString(response.getEntity());

            parseInput(temp);
            // System.out.println("Latitude: " + lat);
            // System.out.println("Longitude: " + lon);

            coordinates[0] = lat;
            coordinates[1] = lon;

            // System.out.println(response.toString());
            sb.append(">>> Request <<<\n");
            sb.append(params.toString());
            sb.append("\n\n>>> Response <<<\n");
            sb.append(temp);
        } catch (Exception e) {
            System.err.println(e);
        }
        return coordinates;
    }

    /**
     * parses the Json Object and extracts the latitude and longiture
     * @param input response of the google API
     */
    private void parseInput(String input) {
        for(int i = 0; i < input.length()-2; i++) {
            if(input.substring(i, i+3).equals("lat")) {
                int startIndex = i+6;
                int endIndex = i+12;
                this.lat = Double.parseDouble(input.substring(startIndex, endIndex));
            }
            if(input.substring(i, i+3).equals("lng")) {
                int startIndex = i+6;
                int endIndex = i+12;
                this.lon = Double.parseDouble(input.substring(startIndex, endIndex));
            }
        }
    }

    public double getLat() {
        return this.lat;
    }

    public double getLon() {
        return this.lon;
    }
}