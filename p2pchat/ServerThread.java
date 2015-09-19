package p2pchat;

import java.io.InputStream;
import java.io.OutputStream;

public class ServerThread extends Thread {

    private InputStream is;
    private OutputStream os;

    public ServerThread(InputStream is, OutputStream os) {
        try {
            this.is = is;
            this.os = os;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("inside ServerThread.run()");
        try {
            while (true) {
                os.write(is.read());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
