package resources;

import javax.validation.Constraint;
import javax.ws.rs.*;

public class Config {
    public double userCurrentLat;
    public double userCurrentLon;

/*    @Path("/setConfig")
    @POST
     public void setConfig(@FormParam("lat") double lat, @FormParam("lon") double lon) {
        userCurrentLat = lat;
        userCurrentLon = lon;
        System.out.printf("Set users position:%nLatitude %f%nLongitude%f%n", userCurrentLat, userCurrentLon);
    }*/

    @Path("/setConfig")
    @POST
    public void setConfig(@HeaderParam("test") String test) {
        System.out.println(test);
    }
}
