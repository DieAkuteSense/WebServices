package geolocation;

import org.apache.cxf.configuration.spring.StringBeanDefinitionParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.json.JsonObject;
import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebService
public class GeoLocation {
    public static final String GEOLOCATION_API_URL = "https://www.googleapis.com/geolocation/v1/geolocate";
    public static final String GEOLOCATION_API_KEY = "AIzaSyDEhCTh03VykiRtsic2ii6GMmFL6Q6ezvY"; // get your own key under https://developers.google.com/maps/documentation/geolocation/get-api-key?hl=de
    public double lat;
    public double lon;

    public GeoLocation() {
    }

    @Path("/getLocation")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public List<Double> requestLocation() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        ResponseData result = new ResponseData();
        HttpResponse response;
        List<Double> doubles = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try {
            // Creating HTTP Post Request to Google Geolocation API //
            HttpPost request = new HttpPost("https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyDEhCTh03VykiRtsic2ii6GMmFL6Q6ezvY");
            StringEntity params = new StringEntity("{\"considerIp\": \"true\"}");
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            // System.out.println(request.toString());
            // Get response from Google Geolocation API and parse it to Json Object
            response = httpClient.execute(request);
            String temp = EntityUtils.toString(response.getEntity());
            // System.out.println(temp);

            parseInput(temp);
            // System.out.println("Latitude: " + lat);
            // System.out.println("Longitude: " + lon);

            doubles.add(lat);
            doubles.add(lon);

            // System.out.println(response.toString());
            sb.append(">>> Request <<<\n");
            sb.append(params.toString());
            sb.append("\n\n>>> Response <<<\n");
            sb.append(temp);
        } catch (Exception e) {
            System.err.println(e);
        }
        return doubles;
    }

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