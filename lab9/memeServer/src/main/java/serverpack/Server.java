package serverpack;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public void run(Integer port) throws IOException {
        String endCommand = "exit";
        ArrayList<User> users = new ArrayList<>();

        /*
            socket closes when you stop the program
         */
        ServerSocket serverSocket = new ServerSocket(port) {
            protected void finalize() throws IOException {
                this.close();
            }
        };

        while (true) {
            Socket clientSocket = serverSocket.accept();

            PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine = fromClient.readLine();
            checkCommand(inputLine, toClient, users);

            toClient.close();
            fromClient.close();
            clientSocket.close();
        }
    }

    public void checkCommand(String command, PrintWriter printWriter, ArrayList<User> users) {

    }
}
