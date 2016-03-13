package fuelPriceService;

import geolocation.GeoLocation;

import javax.annotation.ManagedBean;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Endpoint;
import java.util.List;

@WebService
public class FuelPriceService {

    @Path("/getPriceInCity")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String getPriceInCity(@FormParam("lat") double lat, @FormParam("lon") double lon) {
        FuelPriceClient fpc = new FuelPriceClient();
        JsonObject jsonArray = fpc.requestCurrentFuelPrice(lat, lon, FuelPriceClient.RADIUS, FuelPriceClient.TYPE, FuelPriceClient.SORT);
        return jsonArray.toString();
    }

    public static void main(String[] argv) {
        Object implementor = new FuelPriceService ();
        String address = "http://localhost:9000/fuelPriceService";
        Endpoint.publish(address, implementor);
    }

    @Path("/requestPriceCurrentLocation")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String requestPriceCurrentLocation() {
        GeoLocation geoLocation = new GeoLocation();
        List<Double> coordinates = geoLocation.requestLocation();
        FuelPriceClient fpc = new FuelPriceClient();
        double lat = coordinates.get(0);
        double lon = coordinates.get(1);
        JsonObject jsonObject = fpc.requestCurrentFuelPrice(lat, lon, FuelPriceClient.RADIUS, FuelPriceClient.TYPE, FuelPriceClient.SORT);
        System.out.println(">>> OUTPUT <<<\n" + jsonObject);
        return jsonObject.toString();
    }
}
