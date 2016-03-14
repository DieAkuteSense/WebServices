package fuelPriceService;

import javax.json.JsonObject;

public interface IFuelPriceBackend {
    JsonObject requestCurrentFuelPrice(double lat, double lon, int rad, String type, String sort);
}
