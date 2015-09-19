package p2pchat;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket ss;

    public Server(int p) {
        try {
            ss = new ServerSocket(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("inside Server.run()");
        try {
            while (true) {
                Socket s = ss.accept();
                new ServerThread(s.getInputStream(), System.out).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
