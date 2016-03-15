package resources;

import javax.validation.Constraint;
import javax.ws.rs.*;

public class Config implements IConfig{
    public static ConfigData configData;
    @Override
    public void setConfig(double lat, double lon) {
        configData = new ConfigData(lat, lon);
    }
}
