package aiann;

public class Main {

    public static void main(String[] args) throws Exception {
        String file = args[0];
        Processor processor = new Processor();
        processor.process(file);
    }
}
