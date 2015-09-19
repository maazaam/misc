package p2pchat;

import java.net.Socket;

public class Client {

    private Socket s;

    public Client(String h, int p) {
        try {
            s = new Socket(h, p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("inside Client.run()");
        try {
            new ClientThread(System.in, s.getOutputStream()).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
