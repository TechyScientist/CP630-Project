package net.johnnyconsole.cp630.project.ejb.impl;

import net.johnnyconsole.cp630.project.ejb.interfaces.TemperatureStatelessLocal;
import net.johnnyconsole.cp630.project.ejb.interfaces.TemperatureStatelessRemote;
import net.johnnyconsole.cp630.project.persistence.Model;
import net.johnnyconsole.cp630.project.persistence.interfaces.ModelDao;
import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import javax.ejb.EJB;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class TemperatureStateless implements TemperatureStatelessLocal, TemperatureStatelessRemote {

    @EJB
    private ModelDao modelBean;

    public double predict(String modelName, int year, int month, int day) throws Exception {
        Model model = modelBean.getModel(modelName);
        LinearRegression regression = (LinearRegression) (new ObjectInputStream(model.getObject().getBinaryStream()).readObject());
        ArrayList<Attribute> attributes = new ArrayList<>(
                Arrays.asList(new Attribute("Year"),
                        new Attribute("Month"),
                        new Attribute("Day"),
                        new Attribute("Mean Temperature")));

        Instances prediction = new Instances("TemperaturePredict", attributes, 0);
        prediction.add(new DenseInstance(1, new double[] {year, month, day}));
        prediction.setClassIndex(prediction.numAttributes() - 1);
        return regression.classifyInstance(prediction.firstInstance());
    }

}
