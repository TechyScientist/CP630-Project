package net.johnnyconsole.cp630.project.ejb.impl;

import net.johnnyconsole.cp630.project.ejb.interfaces.TemperatureStatelessLocal;
import net.johnnyconsole.cp630.project.persistence.Model;
import net.johnnyconsole.cp630.project.persistence.interfaces.ModelDao;
import weka.classifiers.trees.REPTree;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

@Stateless
public class TemperatureStateless implements TemperatureStatelessLocal {

    @EJB
    private ModelDao modelBean;

    public double predict(String modelName, int year, int month, int day) throws Exception {
        try {
            Model model = modelBean.getModel(modelName);
            REPTree repTree = (REPTree) (new ObjectInputStream(model.getObject().getBinaryStream()).readObject());
            ArrayList<Attribute> attributes = new ArrayList<>(
                    Arrays.asList(new Attribute("Year"),
                            new Attribute("Month"),
                            new Attribute("Day"),
                            new Attribute("Mean Temperature")));

            Instances prediction = new Instances("TemperaturePredict", attributes, 0);
            prediction.add(new DenseInstance(1, new double[]{year, month, day}));
            prediction.setClassIndex(prediction.numAttributes() - 1);
            return (int) (100 * repTree.classifyInstance(prediction.firstInstance())) / 100.0;
        } catch(NullPointerException e) {
            return Double.MIN_VALUE;
        }
    }

}
