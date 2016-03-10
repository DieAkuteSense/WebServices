package FuelPriceService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by Olli on 09.03.2016.
 */
@WebService()
public class FuelPriceService {
  @WebMethod
  public static void getPriceInCity(String cityID) {
    FuelPriceClient fpc = new FuelPriceClient();
    fpc.requestCurrentFuelPrice(FuelPriceClient.CITY_LAT, FuelPriceClient.CITY_LON, FuelPriceClient.RADIUS, FuelPriceClient.TYPE, FuelPriceClient.SORT);
  }
  public static void main(String[] argv) {
    Object implementor = new FuelPriceService ();
    String address = "http://localhost:9000/FuelPriceService";
    Endpoint.publish(address, implementor);
  }
}
