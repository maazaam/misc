package aiann;

import java.io.FileReader;
import java.util.Properties;

public class Setting {

    private String mode;
    private int inputNode;
    private int hiddenNode;
    private int outputNode;
    private String inputFile;
    private String targetOutputFile;
    private String actualOutputFile;
    private String inputWeightFile;
    private String outputWeightFile;
    private double rate;
    private double momentum;
    private int iteration;

    public Setting(String file) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        mode = properties.getProperty(Constant.MODE);
        inputNode = Integer.parseInt(properties.getProperty(Constant.INPUT_NODE));
        hiddenNode = Integer.parseInt(properties.getProperty(Constant.HIDDEN_NODE));
        outputNode = Integer.parseInt(properties.getProperty(Constant.OUTPUT_NODE));
        inputFile = properties.getProperty(Constant.INPUT_FILE);
        targetOutputFile = properties.getProperty(Constant.TARGET_OUTPUT_FILE);
        actualOutputFile = properties.getProperty(Constant.ACTUAL_OUTPUT_FILE);
        inputWeightFile = properties.getProperty(Constant.INPUT_WEIGHT_FILE);
        outputWeightFile = properties.getProperty(Constant.OUTPUT_WEIGHT_FILE);
        rate = Double.parseDouble(properties.getProperty(Constant.RATE));
        momentum = Double.parseDouble(properties.getProperty(Constant.MOMENTUM));
        iteration = Integer.parseInt(properties.getProperty(Constant.ITERATION));
    }

    public String getMode() throws Exception {
        return mode;
    }

    public int getInputNode() throws Exception {
        return inputNode;
    }

    public int getHiddenNode() throws Exception {
        return hiddenNode;
    }

    public int getOutputNode() throws Exception {
        return outputNode;
    }

    public String getInputFile() throws Exception {
        return inputFile;
    }

    public String getTargetOutputFile() throws Exception {
        return targetOutputFile;
    }

    public String getActualOutputFile() throws Exception {
        return actualOutputFile;
    }

    public String getInputWeightFile() throws Exception {
        return inputWeightFile;
    }

    public String getOutputWeightFile() throws Exception {
        return outputWeightFile;
    }

    public double getRate() throws Exception {
        return rate;
    }

    public double getMomentum() throws Exception {
        return momentum;
    }

    public int getIteration() throws Exception {
        return iteration;
    }
}
