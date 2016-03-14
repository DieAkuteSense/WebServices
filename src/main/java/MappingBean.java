import fuelPriceService.FuelPriceBackend;
import fuelPriceService.FuelPriceService;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

@ManagedBean
public class MappingBean {
    public void buttonAction(ActionEvent actionEvent) {
        FuelPriceBackend fpc = new FuelPriceBackend();
        fpc.requestCurrentFuelPrice(FuelPriceBackend.CITY_LAT, FuelPriceBackend.CITY_LON, FuelPriceBackend.RADIUS, FuelPriceBackend.TYPE, FuelPriceBackend.SORT);
    }

    public void currentLocation() {
        FuelPriceService fuelPriceService = new FuelPriceService();
        fuelPriceService.requestPriceCurrentLocation(FuelPriceBackend.RADIUS, FuelPriceBackend.TYPE);

    }
}
