package FuelPriceService;

import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;

/**
 * Created by Olli on 09.03.2016.
 */
@XmlRootElement
public class FuelPriceData {
    public static class Coordinates {
        private float lon;
        private float lat;

        public float getLon() {
            return lon;
        }

        public void setLon(float lon) {
            this.lon = lon;
        }

        public float getLat() {
            return lat;
        }

        public void setLat(float lat) {
            this.lat = lat;
        }
    }

    private Coordinates coords;

    public Coordinates getCoords() {
        return coords;
    }

    public void setCoords(Coordinates coords) {
        this.coords = coords;
    }

    private List<Element> otherElements;

    @XmlAnyElement
    public List<Element> getOtherElements() {
        return otherElements;
    }

    public void setOtherElements(List<Element> otherElements) {
        this.otherElements = otherElements;
    }
}
