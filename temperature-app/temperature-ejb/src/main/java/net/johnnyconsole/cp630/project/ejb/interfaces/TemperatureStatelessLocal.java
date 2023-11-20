package net.johnnyconsole.cp630.project.ejb.interfaces;

import javax.ejb.Local;

@Local
public interface TemperatureStatelessLocal {

    double predict(String model, int year, int month, int day) throws Exception;

}
