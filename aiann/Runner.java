package aiann;

public class Runner {

    public void run(Setting setting) throws Exception {
        int inputNode = setting.getInputNode();
        int hiddenNode = setting.getHiddenNode();
        int outputNode = setting.getOutputNode();
        String inputFile = setting.getInputFile();
        String actualOutputFile = setting.getActualOutputFile();
        String inputWeightFile = setting.getInputWeightFile();
        String outputWeightFile = setting.getOutputWeightFile();
        Reader reader = new Reader();
        Normalizer normalizer = new Normalizer();
        double[][] array = reader.read(inputFile);
        double[][] inputs = normalizer.normalize(array);
        double[][] inputWeight = reader.read(inputWeightFile);
        double[][] outputWeight = reader.read(outputWeightFile);
        Network network = new Network(inputNode, hiddenNode, outputNode, inputWeight, outputWeight);
        double[][] outputs = new double[inputs.length][outputNode];
        for (int i = 0; i < inputs.length; i++) {
            double[] output = network.computeOutput(inputs[i]);
            for (int j = 0; j < output.length; j++) {
                outputs[i][j] = output[j];
            }
        }
        Writer writer = new Writer();
        writer.write(outputs, actualOutputFile);
    }
}
