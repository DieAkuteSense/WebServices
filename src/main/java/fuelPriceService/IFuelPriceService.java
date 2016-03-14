package fuelPriceService;

import javax.jws.WebParam;
import javax.ws.rs.FormParam;

/**
 * Created by Olli on 09.03.2016.
 */
public interface IFuelPriceService {
    String getPriceInCity(@FormParam("lat") double lat, @FormParam("lon") double lon, @FormParam("rad") int rad, @FormParam("type") String type);
    String requestPriceCurrentLocation(@FormParam("rad") int rad, @FormParam("type") String type);
}
