package aiann;

public class Network {

    private int inputNode;
    private int hiddenNode;
    private int outputNode;
    private double[] inputLayer;
    private double[] hiddenLayer;
    private double[] outputLayer;
    private double[][] inputWeight;
    private double[][] outputWeight;
    private double[][] inputChange;
    private double[][] outputChange;

    public Network(int inputNode, int hiddenNode, int outputNode) throws Exception {
        this.inputNode = inputNode + 1;
        this.hiddenNode = hiddenNode;
        this.outputNode = outputNode;
        this.inputLayer = new double[this.inputNode];
        this.hiddenLayer = new double[this.hiddenNode];
        this.outputLayer = new double[this.outputNode];
        this.inputWeight = new double[this.inputNode][this.hiddenNode];
        this.outputWeight = new double[this.hiddenNode][this.outputNode];
        this.inputChange = new double[this.inputNode][this.hiddenNode];
        this.outputChange = new double[this.hiddenNode][this.outputNode];
        for (int i = 0; i < this.inputNode; i++) {
            this.inputLayer[i] = 1.0;
        }
        for (int j = 0; j < this.hiddenNode; j++) {
            this.hiddenLayer[j] = 1.0;
        }
        for (int k = 0; k < this.outputNode; k++) {
            this.outputLayer[k] = 1.0;
        }
        for (int i = 0; i < this.inputNode; i++) {
            for (int j = 0; j < this.hiddenNode; j++) {
                this.inputWeight[i][j] = random(0.0, 1.0);
            }
        }
        for (int j = 0; j < this.hiddenNode; j++) {
            for (int k = 0; k < this.outputNode; k++) {
                this.outputWeight[j][k] = random(0.0, 1.0);
            }
        }
        for (int i = 0; i < this.inputNode; i++) {
            for (int j = 0; j < this.hiddenNode; j++) {
                this.inputChange[i][j] = 0.0;
            }
        }
        for (int j = 0; j < this.hiddenNode; j++) {
            for (int k = 0; k < this.outputNode; k++) {
                this.outputChange[j][k] = 0.0;
            }
        }
    }

    public Network(int inputNode, int hiddenNode, int outputNode, double[][] inputWeight, double[][] outputWeight)
            throws Exception {
        this(inputNode, hiddenNode, outputNode);
        this.inputWeight = inputWeight;
        this.outputWeight = outputWeight;
    }

    private double random(double min, double max) throws Exception {
        return (max - min) * Math.random() + min;
    }

    private double activate(double value) throws Exception {
        return Math.tanh(value);
    }

    private double derivate(double value) throws Exception {
        return 1.0 - Math.pow(value, 2.0);
    }

    public double[] computeOutput(double[] input) throws Exception {
        for (int i = 0; i < inputNode - 1; i++) {
            inputLayer[i] = input[i];
        }
        for (int j = 0; j < hiddenNode; j++) {
            double sum = 0.0;
            for (int i = 0; i < inputNode; i++) {
                sum += inputLayer[i] * inputWeight[i][j];
            }
            hiddenLayer[j] = activate(sum);
        }
        for (int k = 0; k < outputNode; k++) {
            double sum = 0.0;
            for (int j = 0; j < hiddenNode; j++) {
                sum += hiddenLayer[j] * outputWeight[j][k];
            }
            outputLayer[k] = activate(sum);
        }
        return outputLayer;
    }

    public double computeError(double[] targetOutput, double[] actualOutput) throws Exception {
        double error = 0.0;
        for (int k = 0; k < outputNode; k++) {
            error += Math.pow(targetOutput[k] - actualOutput[k], 2.0);
        }
        return error / 2.0;
    }

    public void adjustWeight(double[] targetOutput, double[] actualOutput, double rate, double momentum)
            throws Exception {
        double[] outputDelta = new double[outputNode];
        for (int k = 0; k < outputNode; k++) {
            double error = targetOutput[k] - actualOutput[k];
            outputDelta[k] = derivate(outputLayer[k]) * error;
        }
        double[] hiddenDelta = new double[hiddenNode];
        for (int j = 0; j < hiddenNode; j++) {
            double error = 0.0;
            for (int k = 0; k < outputNode; k++) {
                error += outputDelta[k] * outputWeight[j][k];
            }
            hiddenDelta[j] = derivate(hiddenLayer[j]) * error;
        }
        for (int j = 0; j < hiddenNode; j++) {
            for (int k = 0; k < outputNode; k++) {
                double change = outputDelta[k] * hiddenLayer[j];
                outputWeight[j][k] += rate * change + momentum * outputChange[j][k];
                outputChange[j][k] = change;
            }
        }
        for (int i = 0; i < inputNode; i++) {
            for (int j = 0; j < hiddenNode; j++) {
                double change = hiddenDelta[j] * inputLayer[i];
                inputWeight[i][j] += rate * change + momentum * inputChange[i][j];
                inputChange[i][j] = change;
            }
        }
    }

    public double[][] getInputWeight() throws Exception {
        return inputWeight;
    }

    public double[][] getOutputWeight() throws Exception {
        return outputWeight;
    }
}
