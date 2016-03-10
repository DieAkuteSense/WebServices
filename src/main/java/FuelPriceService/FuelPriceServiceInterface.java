package FuelPriceService;

/**
 * Created by Olli on 09.03.2016.
 */
public interface FuelPriceServiceInterface {
    String getVersion();

    void requestCurrentPrice() throws FuelPriceServiceException;
}
