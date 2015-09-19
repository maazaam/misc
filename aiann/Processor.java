package aiann;

public class Processor {

    public void process(String file) throws Exception {
        Setting setting = new Setting(file);
        String mode = setting.getMode();
        if (mode.equals("train")) {
            Trainer trainer = new Trainer();
            trainer.train(setting);
        }
        if (mode.equals("test")) {
            Tester tester = new Tester();
            tester.test(setting);
        }
        if (mode.equals("run")) {
            Runner runner = new Runner();
            runner.run(setting);
        }
    }
}
