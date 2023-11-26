package net.johnnyconsole.cp630.project.ws.impl;

import net.johnnyconsole.cp630.project.ejb.interfaces.TemperatureStatelessLocal;
import net.johnnyconsole.cp630.project.ws.interfaces.TemperatureWS;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.servlet.annotation.WebServlet;

@WebService(endpointInterface="net.johnnyconsole.cp630.project.ws.interfaces.TemperatureWS")
public class TemperatureWSImpl implements TemperatureWS {

    @EJB
    private TemperatureStatelessLocal stateless;

    @Override
    public double predict(String model, int year, int month, int day) throws Exception {
        return stateless.predict(model, year, month, day);
    }
}
