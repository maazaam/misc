package p2pchat;

public class ServerWorker extends Thread {

    private int p;

    public ServerWorker(int p) {
        try {
            this.p = p;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("inside ServerWorker.run()");
        try {
            new Server(p).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
