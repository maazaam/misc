package aiann;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Reader {

    public double[][] read(String file) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> list = new ArrayList<String>();
        String line = new String();
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        int row = list.size();
        int col = list.get(0).split(", ").length;
        double[][] array = new double[row][col];
        for (int i = 0; i < row; i++) {
            String[] temp = list.get(i).split(", ");
            for (int j = 0; j < col; j++) {
                array[i][j] = Double.parseDouble(temp[j]);
            }
        }
        return array;
    }
}
