package net.johnnyconsole.cp630.project.ws.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style= SOAPBinding.Style.RPC)
public interface TemperatureWS {
    @WebMethod
    double predict(String model, int year, int month, int day) throws Exception;

}
