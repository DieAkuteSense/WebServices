package fuelPriceService;

import geolocation.GeoLocation;
import resources.Config;
import resources.ConfigData;

import javax.json.JsonObject;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Endpoint;

@WebService
public class FuelPriceService implements IFuelPriceService {

    @Override @POST
    @Path("/userLocatedPrice") @Produces(MediaType.TEXT_PLAIN)
    public String userLocatedPrice(@HeaderParam("lat") double lat, @HeaderParam("lon") double lon, @HeaderParam("rad") int rad, @HeaderParam("type") String type, @HeaderParam("sort") String sort) {
        Config config = new Config();
        FuelPriceBackend fpb = new FuelPriceBackend();

        if(type.equals("all")) {
            sort = "dist";
        }

        config.setConfig(lat, lon);

        JsonObject jsonObject = fpb.requestCurrentFuelPrice(lat, lon, rad, type, sort);

        System.out.println("--------------------------------------------------------------------");
        System.out.printf("User input:%nRadius: %s%nFuel type: %s%n", rad, type);
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("User location:%nLatitude: %f%nLongitude: %f%n", lat, lon);
        System.out.println("--------------------------------------------------------------------");
        System.out.println(">>> OUTPUT <<<\n" + jsonObject);
        System.out.println("--------------------------------------------------------------------");

        return jsonObject.toString();
    }

    @Override @POST
    @Path("/geoLocatedPrice") @Produces(MediaType.TEXT_PLAIN)
    public String geoLocatedPrice(@HeaderParam("rad") int rad, @HeaderParam("type") String type, @HeaderParam("sort") String sort) {
        GeoLocation geoLocation = new GeoLocation();
        Config config = new Config();
        FuelPriceBackend fbp = new FuelPriceBackend();

        if(type.equals("all")) {
            sort = "dist";
        }

        Double[] coordinates = geoLocation.requestLocation();
        double lat = coordinates[0];
        double lon = coordinates[1];

        config.setConfig(lat, lon);

        JsonObject jsonObject = fbp.requestCurrentFuelPrice(lat, lon, rad, type, sort);

        System.out.println("--------------------------------------------------------------------");
        System.out.printf("User input:%nRadius: %s%nFuel type: %s%n", rad, type);
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("Ascertained location:%nLatitude: %f%nLongitude: %f%n", lat, lon);
        System.out.println("--------------------------------------------------------------------");
        System.out.println(">>> OUTPUT <<<\n" + jsonObject);
        System.out.println("--------------------------------------------------------------------");

        return jsonObject.toString();
    }

    public static void main(String[] argv) {
        Object implementor = new FuelPriceService ();
        String address = "http://localhost:9000/fuelPriceService";
        Endpoint.publish(address, implementor);
    }
}
