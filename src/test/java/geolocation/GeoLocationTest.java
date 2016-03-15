package geolocation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Olli on 15.03.2016.
 */
public class GeoLocationTest {

    @Test
    public void testRequestLocation() throws Exception {
        GeoLocation geoLocation = new GeoLocation();
        Double[] coordinates = geoLocation.requestLocation();

        assertNotNull(coordinates);
    }
}