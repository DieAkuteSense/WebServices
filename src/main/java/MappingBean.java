import fuelPriceService.FuelPriceClient;
import fuelPriceService.FuelPriceService;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.json.JsonArray;

/**
 * Created by Olli on 12.03.2016.
 */
@ManagedBean
public class MappingBean {
    public void buttonAction(ActionEvent actionEvent) {
        FuelPriceClient fpc = new FuelPriceClient();
        fpc.requestCurrentFuelPrice(FuelPriceClient.CITY_LAT, FuelPriceClient.CITY_LON, FuelPriceClient.RADIUS, FuelPriceClient.TYPE, FuelPriceClient.SORT);
    }

    public void currentLocation() {
        FuelPriceService fuelPriceService = new FuelPriceService();
        fuelPriceService.requestPriceCurrentLocation();

    }
}
