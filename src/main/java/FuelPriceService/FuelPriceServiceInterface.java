package fuelPriceService;

import javax.jws.WebParam;

/**
 * Created by Olli on 09.03.2016.
 */
public interface FuelPriceServiceInterface {
    String returnVersion();

    double requestCurrentPrice(@WebParam(name = "lat") double lat, @WebParam(name = "lon") double lon) throws FuelPriceServiceException;
}
