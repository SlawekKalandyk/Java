package serverpack;

import java.io.IOException;

public class MainClass {
    public static void main(String[] argv) {
        Integer port = 6896;
        Server server = new Server();

        try {
            server.run(port);
        } catch(IOException ioEx) {
            ioEx.printStackTrace();
        }

    }
}
