package fuelPriceService;

import javax.json.JsonArray;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Endpoint;

@WebService
public class FuelPriceService {

    @Path("/getPriceInCity")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
  public String getPriceInCity(@FormParam("lat") double lat, @FormParam("lon") double lon) {
    FuelPriceClient fpc = new FuelPriceClient();
    JsonArray jsonArray = fpc.requestCurrentFuelPrice(lat, lon, FuelPriceClient.RADIUS, FuelPriceClient.TYPE, FuelPriceClient.SORT);
    return jsonArray.toString();

  }
  public static void main(String[] argv) {
    Object implementor = new FuelPriceService ();
    String address = "http://localhost:9000/fuelPriceService";
    Endpoint.publish(address, implementor);
  }
}
