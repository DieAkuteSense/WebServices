package fuelPriceService;

import geolocation.GeoLocation;

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
    public String getPriceInCity(@FormParam("lat") double lat, @FormParam("lon") double lon, @FormParam("rad") int rad) {
        System.out.println("lat :" + lat + "\nlon: " + lon);
        FuelPriceClient fpc = new FuelPriceClient();
        JsonObject jsonObject = fpc.requestCurrentFuelPrice(lat, lon, rad, FuelPriceClient.TYPE, FuelPriceClient.SORT);
        System.out.println(">>> OUTPUT <<<\n" + jsonObject);
        return jsonObject.toString();
    }

    public static void main(String[] argv) {
        Object implementor = new FuelPriceService ();
        String address = "http://localhost:9000/fuelPriceService";
        Endpoint.publish(address, implementor);
    }

    @Path("/requestPriceCurrentLocation")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String requestPriceCurrentLocation(@FormParam("rad") int rad) {
        GeoLocation geoLocation = new GeoLocation();
        List<Double> coordinates = geoLocation.requestLocation();
        FuelPriceClient fpc = new FuelPriceClient();
        double lat = coordinates.get(0);
        double lon = coordinates.get(1);
        System.out.println("lat :" + lat + "\nlon: " + lon);
        JsonObject jsonObject = fpc.requestCurrentFuelPrice(lat, lon, rad, FuelPriceClient.TYPE, FuelPriceClient.SORT);
        System.out.println(">>> OUTPUT <<<\n" + jsonObject);
        return jsonObject.toString();
    }
}
