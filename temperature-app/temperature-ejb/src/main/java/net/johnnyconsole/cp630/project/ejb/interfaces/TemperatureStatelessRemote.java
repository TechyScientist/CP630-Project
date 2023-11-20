package net.johnnyconsole.cp630.project.ejb.interfaces;

import javax.ejb.Remote;

@Remote
public interface TemperatureStatelessRemote {

    double predict(String model, int year, int month, int day) throws Exception;

}
