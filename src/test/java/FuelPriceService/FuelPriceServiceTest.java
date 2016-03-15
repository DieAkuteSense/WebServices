package fuelPriceService;

import org.junit.Test;
import static org.junit.Assert.*;
import javax.json.JsonObject;

/**
 * Created by Olli on 09.03.2016.
 */
public class FuelPriceServiceTest {

    @Test
    public void testGetPriceInCity() {
        FuelPriceBackend fuelPriceBackend = new FuelPriceBackend();
        JsonObject jsonObject = fuelPriceBackend.requestCurrentFuelPrice(FuelPriceBackend.CITY_LAT, FuelPriceBackend.CITY_LON, FuelPriceBackend.RADIUS, FuelPriceBackend.TYPE, FuelPriceBackend.SORT);

        assertNotNull(jsonObject);
    }
}