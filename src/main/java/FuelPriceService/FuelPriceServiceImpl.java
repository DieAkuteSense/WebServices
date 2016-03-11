package fuelPriceService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.ws.rs.POST;

@WebService()
public class FuelPriceServiceImpl implements FuelPriceServiceInterface {

    private FuelPriceClient fpc = new FuelPriceClient();

    @Override
    @WebMethod
    public String returnVersion() {
        return "0.01 testing build";
    }

    @Override
    @WebMethod
    public double requestCurrentPrice(@WebParam(name="lat") double lat, @WebParam(name="lon") double lon) throws FuelPriceServiceException {
        fpc.requestCurrentFuelPrice(lat, lon, FuelPriceClient.RADIUS, FuelPriceClient.TYPE, FuelPriceClient.SORT);
        return lat;
    }
}
