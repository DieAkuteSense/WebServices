package fuelPriceService;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class FuelPriceClient {
    /**
     * Configuration
     */
    public static final String TANKERKOENIG_API_URL = "https://creativecommons.tankerkoenig.de/json/list.php";
    public static final String TANKERKOENIG_API_KEY = "00000000-0000-0000-0000-000000000002"; // just testing around ;)
  //  public static final String TANKERKOENIG_API_KEY = "671b939e-08ee-8807-be55-e1bd540c210b"; // get your own API key under https://creativecommons.tankerkoenig.de/#register
    public static final double CITY_LAT = 48.8088277717712;
    public static final double CITY_LON = 9.224395751953125;
    public static final int RADIUS = 2;
    public static final String TYPE = "diesel";
    public static final String SORT = "price";
    private final WebTarget wt;
    /**
     * Configuration end
     */

    public FuelPriceClient() {
        final Client client = ClientBuilder.newClient();
        wt = client.target(TANKERKOENIG_API_URL).queryParam("apikey", TANKERKOENIG_API_KEY);
    }

    // TODO maybe return type should be a list of the stations
    public void requestCurrentFuelPrice(double lat, double lon, int rad, String type, String sort) {
        final Response response = wt.queryParam("lat", lat).queryParam("lng", lon).queryParam("rad", rad).queryParam("sort", sort).queryParam("type", type).request().get();
        //System.out.println(response.toString());
        final JsonObject jsonObject = Json.createReader(response.readEntity(InputStream.class)).readObject();
        //System.out.println(jsonObject.toString());
        final JsonArray mainData = jsonObject.getJsonArray("stations");
        final List<JsonObject> stations = getStations(mainData);
        getDetails(stations);
    }

    private StationData jsonResponseToFuelPrice(final JsonObject jsonObject) {
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

        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------------------------------------------");
        sb.append("\nSelected station:");
        sb.append("\nName: " + name);
        sb.append("\nPrice: " + price);
        sb.append("\nStation ID: " + id);
        sb.append("\n-----------------------------------------------------------");
        System.out.println(sb.toString());

        return new StationData(name, lat, lon, brand, dist, price, id, street, houseNumber, postCode, place, isOpen);
    }

    private List<JsonObject> getStations(JsonArray jsonArray) {
        List<JsonObject> names = new ArrayList<>();
        for(int i = 0; i < jsonArray.size(); i++) {
            names.add(jsonArray.getJsonObject(i));
        }
        // System.out.println("Found stations: " + names.toString());
        return names;
    }

    // TODO just selects the 1 value of the object-list, has to select the user-selected value
    private StationData getDetails(List<JsonObject> data) {
        JsonObject detailedInformation;
        detailedInformation = data.get(0);
        return jsonResponseToFuelPrice(detailedInformation);
    }
}
