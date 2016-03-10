package FuelPriceService;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Olli on 09.03.2016.
 */
@WebService(name = "priceService", serviceName = "priceService")
public class FuelPriceServiceImpl implements FuelPriceServiceInterface {
    private final FuelPriceClient fpc = new FuelPriceClient();

    @Override
    @WebMethod
    public String getVersion() {
        return "0.01 testing build";
    }

    @Override
    public void requestCurrentPrice() throws FuelPriceServiceException {
        FuelPriceClient fpc = new FuelPriceClient();
        fpc.requestCurrentFuelPrice(FuelPriceClient.CITY_LAT, FuelPriceClient.CITY_LON, FuelPriceClient.RADIUS, FuelPriceClient.TYPE, FuelPriceClient.SORT);
    }
}
