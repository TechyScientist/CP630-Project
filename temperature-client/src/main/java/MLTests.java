
import weka.classifiers.functions.GaussianProcesses;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.REPTree;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ConverterUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MLTests {

    public static void main(String[] args) throws Exception {
        LinearRegression regression = new LinearRegression();
        ZeroR zeroR = new ZeroR();
        REPTree repTree = new REPTree();
        SMOreg smOreg = new SMOreg();
        GaussianProcesses gaussianProcesses = new GaussianProcesses();

        File dataset = new File("C:/Users/console/Documents/CP630/CP630-Project/Proposal/files/TemperatureData-2013-2023-Combined.arff");

        Instances dataInstances = ConverterUtils.DataSource.read(dataset.getPath());
        dataInstances.setClassIndex(dataInstances.numAttributes() - 1);

        SelectedTag tag = new SelectedTag(LinearRegression.SELECTION_NONE, LinearRegression.TAGS_SELECTION);
        regression.setAttributeSelectionMethod(tag);
        regression.buildClassifier(dataInstances);

        zeroR.buildClassifier(dataInstances);
        repTree.buildClassifier(dataInstances);
        smOreg.buildClassifier(dataInstances);
        gaussianProcesses.buildClassifier(dataInstances);

        ArrayList<Attribute> attributes = new ArrayList<>(
                Arrays.asList(new Attribute("Year"),
                        new Attribute("Month"),
                        new Attribute("Day"),
                        new Attribute("Mean Temperature")));

        Instances prediction = new Instances("TemperaturePredict", attributes, 0);

        prediction.setClassIndex(prediction.numAttributes() - 1);
        System.out.println("Predictions for 1st of every month in  2024:");
        for (int i = 1; i <=12 ; i++) {
            System.out.println("Month: " + i);

            prediction.add(new DenseInstance(1, new double[]{2024, i, 1}));
            double r = regression.classifyInstance(prediction.firstInstance());
            System.out.println("LinearRegression: " + r);
            double z = zeroR.classifyInstance(prediction.firstInstance());
            System.out.println("ZeroR: " + z);
            double s = smOreg.classifyInstance(prediction.firstInstance());
            System.out.println("SMOReg: " + s);
            double t = repTree.classifyInstance(prediction.firstInstance());
            System.out.println("RepTree: " + t);
            double g = gaussianProcesses.classifyInstance(prediction.firstInstance());
            System.out.println("GaussianProcesses: " + g);
            System.out.println("\n");

            prediction.remove(0);
        }
    }
}
