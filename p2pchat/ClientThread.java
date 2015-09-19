package p2pchat;

import java.io.InputStream;
import java.io.OutputStream;

public class ClientThread extends Thread {

    private InputStream is;
    private OutputStream os;

    public ClientThread(InputStream is, OutputStream os) {
        try {
            this.is = is;
            this.os = os;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("inside ClientThread.run()");
        try {
            while (true) {
                os.write(is.read());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
