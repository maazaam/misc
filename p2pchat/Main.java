package p2pchat;

public class Main {

    public static void main(String[] args) {
        try {
            new ServerWorker(7777).start();
            new ClientWorker("localhost", 7777).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
