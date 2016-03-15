package fuelPriceService;

import javax.jws.WebParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;

/**
 * Created by Olli on 09.03.2016.
 */
public interface IFuelPriceService {
    String userLocatedPrice(@HeaderParam("lat") double lat, @HeaderParam("lon") double lon, @HeaderParam("rad") int rad, @HeaderParam("type") String type, @HeaderParam("sort") String sort);
    String geoLocatedPrice(@HeaderParam("rad") int rad, @HeaderParam("type") String type, @HeaderParam("sort") String sort);
}
