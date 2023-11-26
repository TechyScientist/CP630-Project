package net.johnnyconsole.cp630.project.rs;

import net.johnnyconsole.cp630.project.ejb.interfaces.TemperatureStatelessLocal;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/")
@RequestScoped
public class TemperatureRS {
    @EJB
    private TemperatureStatelessLocal stateless;

    @GET
    @Path("/predict")
    @Produces("application/json")
    public String predict(@QueryParam("model") String model, @QueryParam("year") int year,
                          @QueryParam("month") int month, @QueryParam("day") int day) throws Exception {
        return "{\"result\":" + stateless.predict(model, year, month, day) + "}";
    }

}
