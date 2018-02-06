package fuelPriceService;

import resources.Config;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.jws.WebService;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@WebService
public class FuelPriceBackend implements IFuelPriceBackend{
    /**
     * Configuration
     */
    public static final String TANKERKOENIG_API_URL = "https://creativecommons.tankerkoenig.de/json/list.php";
    public static final String TANKERKOENIG_API_KEY = ""; // get your own API key under https://creativecommons.tankerkoenig.de/#register
    public static final double CITY_LAT = 48.8088277717712;
    public static final double CITY_LON = 9.224395751953125;
    public static final int RADIUS = 2;
    public static final String TYPE = "diesel";
    public static final String SORT = "price";
    private final  WebTarget wt;

    /**
     * Configuration end
     */

    public FuelPriceBackend() {
        SSLContext sc;
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (GeneralSecurityException e) {
            sc = null;
        }
        final Client client = ClientBuilder.newBuilder().sslContext(sc).build();
        wt = client.target(TANKERKOENIG_API_URL).queryParam("apikey", TANKERKOENIG_API_KEY);
    }

    @SuppressWarnings("ValidExternallyBoundObject")
    @Override
    public JsonObject requestCurrentFuelPrice(double lat, double lon, int rad, String type, String sort) {
        final Response response = wt
                .queryParam("lat", lat)
                .queryParam("lng", lon)
                .queryParam("rad", rad)
                .queryParam("sort", sort)
                .queryParam("type", type)
                .request()
                .get();


        final JsonObject jsonObject = Json.createReader(response.readEntity(InputStream.class)).readObject();
        getStations(jsonObject.getJsonArray("stations"));
        return jsonObject;
    }

    private StationData createStationDataObjects(final JsonObject jsonObject) {

        try {
            final String name = jsonObject.getString("name");
            final double lat = jsonObject.getJsonNumber("lat").doubleValue();
            final double lon = jsonObject.getJsonNumber("lng").doubleValue();
            final String brand = jsonObject.getString("brand");
            final double dist = jsonObject.getJsonNumber("dist").doubleValue();
            final double price = jsonObject.getJsonNumber("price").doubleValue();
            final String id = jsonObject.getString("id");
            final String street = jsonObject.getString("street");
            final String houseNumber = jsonObject.getString("houseNumber");
            final int postCode = jsonObject.getJsonNumber("postCode").intValue();
            final String place = jsonObject.getString("place");
            final boolean isOpen = jsonObject.getBoolean("isOpen");
            final double userLat = Config.configData.getUserLat();
            final double userLon = Config.configData.getUserLon();

            StringBuilder sb = new StringBuilder();
            sb.append("-----------------------------------------------------------");
            sb.append("\nSelected station:");
            sb.append("\nName: " + name);
            sb.append("\nPrice: " + price);
            sb.append("\nStation ID: " + id);
            sb.append("\nUser Latitude: " + userLat);
            sb.append("\nUser Longitude: " + userLon);
            sb.append("\n-----------------------------------------------------------");
            System.out.println(sb.toString());

            return new StationData(name, lat, lon, brand, dist, price, id, street, houseNumber, postCode, place, isOpen, userLat, userLon);
        } catch(Exception e) {
            System.err.println("Please check if a value is NULL:\n" + jsonObject.toString() + "\n");
        }
        // TODO exception handling (if a value is NULL) not very nice
        return new StationData("request failed", 1.0, 1.0, "abc", 1.0, 1.0, "abc", "abc", "abc", 123, "abc", true, 1.0, 1.0);
    }

    // TODO call when user selects a gas station (maybe as POST RESTful Service --> then implement a mapping-method in FuelPriceService)
    private List<JsonObject> getStations(JsonArray jsonArray) {
        List<JsonObject> names = new ArrayList<>();
        for(int i = 0; i < jsonArray.size(); i++) {
            names.add(jsonArray.getJsonObject(i));
        }
        // System.out.println("Found stations: " + names.toString());
        getDetails(names);
        return names;
    }

    // FIXME just selects the 1 value of the object-list, has to select the user-selected value
    private StationData getDetails(List<JsonObject> data) {
        JsonObject detailedInformation;
        detailedInformation = data.get(0);
        return createStationDataObjects(detailedInformation);
    }
}
