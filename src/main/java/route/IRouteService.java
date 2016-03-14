package route;

import fuelPriceService.StationData;


// TODO Andreas und Frederik
public interface IRouteService {
    public Double[] calcDistTime(double startLat, double startLon, double endLat, double endLon);
    public void generateMap(StationData stationData);
}
