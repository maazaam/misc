package p2pchat;

public class ClientWorker extends Thread {

    private String h;
    private int p;

    public ClientWorker(String h, int p) {
        try {
            this.h = h;
            this.p = p;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("inside ClientWorker.run()");
        try {
            new Client(h, p).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
