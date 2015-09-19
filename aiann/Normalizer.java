package aiann;

public class Normalizer {

    public double[][] normalize(double[][] input) throws Exception {
        double[][] inverse = inverse(input);
        for (int i = 0; i < inverse.length; i++) {
            double A = min(inverse[i]);
            double B = max(inverse[i]);
            double a = -1.0;
            double b = +1.0;
            for (int j = 0; j < inverse[i].length; j++) {
                double x = inverse[i][j];
                inverse[i][j] = a + (x - A) * (b - a) / (B - A);
            }
        }
        double[][] output = inverse(inverse);
        return output;
    }

    private double[][] inverse(double[][] input) throws Exception {
        double[][] output = new double[input[0].length][input.length];
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[i].length; j++) {
                output[i][j] = input[j][i];
            }
        }
        return output;
    }

    private double min(double[] input) throws Exception {
        double output = 0.0;
        for (int i = 0; i < input.length; i++) {
            if (output > input[i]) {
                output = input[i];
            }
        }
        return output;
    }

    private double max(double[] input) throws Exception {
        double output = 0.0;
        for (int i = 0; i < input.length; i++) {
            if (output < input[i]) {
                output = input[i];
            }
        }
        return output;
    }
}
