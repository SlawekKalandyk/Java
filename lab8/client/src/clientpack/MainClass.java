package clientpack;

import java.io.IOException;

public class MainClass {
    public static void main(String[] argv) {
        String host = "localhost";
        Integer port = 6896;
        Client client = new Client();
        try {
            client.run(host, port);
        } catch(IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}
