package FuelPriceService;

/**
 * Created by Olli on 09.03.2016.
 */
public class FuelPriceServiceException extends Exception {
    private static final long serialVersionUID = -1234;

    public FuelPriceServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public FuelPriceServiceException(String message) {
        super(message);
    }

    public FuelPriceServiceException(Throwable cause) {
        super(cause);
    }
}
