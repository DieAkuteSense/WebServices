package fuelPriceService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class FuelPriceServiceImpl {

    private FuelPriceClient fpc = new FuelPriceClient();

    @WebMethod
    public String returnVersion() {
        return "0.01 testing build";
    }

    @WebMethod
    public double requestCurrentPrice(@WebParam(name="lat") double lat, @WebParam(name="lon") double lon) throws FuelPriceServiceException {
        fpc.requestCurrentFuelPrice(lat, lon, FuelPriceClient.RADIUS, FuelPriceClient.TYPE, FuelPriceClient.SORT);
        return lat;
    }
}
