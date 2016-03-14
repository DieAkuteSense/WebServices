package fuelPriceService;

import geolocation.GeoLocation;

import javax.json.JsonObject;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Endpoint;

@WebService
public class FuelPriceService implements IFuelPriceService {


    /**
     * Called with RESTful Service under URI /priceService/services/rest/getPriceInCity
     * @param lat (double) user input latitude; necessary to request data for the user-specified location
     * @param lon (double) user input longitude; necessary to request data for the user-specified location
     * @param rad (int) user input radius; necessary to request data in the perimeter of the user-specified location
     * @param type (String) user input type; necessary to request data for the user-specified fuel type
     * @return received data
     */
    @Override
    @Path("/getPriceInCity")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String getPriceInCity(@FormParam("lat") double lat, @FormParam("lon") double lon, @FormParam("rad") int rad, @FormParam("type") String type) {
        System.out.println("lat :" + lat + "\nlon: " + lon);
        FuelPriceBackend fpc = new FuelPriceBackend();
        JsonObject jsonObject = fpc.requestCurrentFuelPrice(lat, lon, rad, type, FuelPriceBackend.SORT);
        System.out.println(">>> OUTPUT <<<\n" + jsonObject);
        return jsonObject.toString();
    }

    public static void main(String[] argv) {
        Object implementor = new FuelPriceService ();
        String address = "http://localhost:9000/fuelPriceService";
        Endpoint.publish(address, implementor);
    }

    /**
     * Called with RESTful Service under URI /priceService/services/rest/requestPriceCurrentLocation
     * @param rad (int) user input radius; necessary to request data in the perimeter of the user-specified location
     * @param type (String) user input type; necessary to request data for the user-specified fuel type
     * @return
     */
    @Override
    @Path("/requestPriceCurrentLocation")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String requestPriceCurrentLocation(@FormParam("rad") int rad, @FormParam("type") String type) {
        GeoLocation geoLocation = new GeoLocation();
        Double[] coordinates = geoLocation.requestLocation();
        FuelPriceBackend fpc = new FuelPriceBackend();
        double lat = coordinates[0];
        double lon = coordinates[1];
        System.out.printf("Ascertained location:%nLatitude: %f%nLongitude: %f%n", lat, lon);
        JsonObject jsonObject = fpc.requestCurrentFuelPrice(lat, lon, rad, type, FuelPriceBackend.SORT);
        System.out.println(">>> OUTPUT <<<\n" + jsonObject);
        return jsonObject.toString();
    }
}
