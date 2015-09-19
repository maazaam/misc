package aiann;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Writer {

    public void write(double[][] array, String file) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                writer.write(String.valueOf(array[i][j]));
                if (j < array[i].length - 1) {
                    writer.write(", ");
                } else {
                    writer.newLine();
                }
            }
        }
        writer.close();
    }
}
