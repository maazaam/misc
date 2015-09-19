package aiann;

public class Trainer {

    public void train(Setting setting) throws Exception {
        int inputNode = setting.getInputNode();
        int hiddenNode = setting.getHiddenNode();
        int outputNode = setting.getOutputNode();
        String inputFile = setting.getInputFile();
        String targetOutputFile = setting.getTargetOutputFile();
        String inputWeightFile = setting.getInputWeightFile();
        String outputWeightFile = setting.getOutputWeightFile();
        double rate = setting.getRate();
        double momentum = setting.getMomentum();
        int iteration = setting.getIteration();
        Reader reader = new Reader();
        Normalizer normalizer = new Normalizer();
        double[][] array = reader.read(inputFile);
        double[][] inputs = normalizer.normalize(array);
        double[][] outputs = reader.read(targetOutputFile);
        Network network = new Network(inputNode, hiddenNode, outputNode);
        for (int i = 0; i < iteration; i++) {
            boolean done = true;
            for (int j = 0; j < inputs.length; j++) {
                double[] output = network.computeOutput(inputs[j]);
                double error = network.computeError(outputs[j], output);
                if (error != 0.0) {
                    done = false;
                    network.adjustWeight(outputs[j], output, rate, momentum);
                }
            }
            if (done) {
                break;
            }
        }
        double[][] inputWeight = network.getInputWeight();
        double[][] outputWeight = network.getOutputWeight();
        Writer writer = new Writer();
        writer.write(inputWeight, inputWeightFile);
        writer.write(outputWeight, outputWeightFile);
    }
}
