package clientpack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
    this class is not really needed anymore since it's no longer a console app,
    saved for reference
 */

public class Client {
    public void run(String host, Integer port) throws IOException {
        Socket socket = new Socket(host, port);
        PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Type a command: ");
        String userInput = clientInput.readLine();
        toServer.println(userInput);
        System.out.println("Server answer: " + fromServer.readLine());

        toServer.close();
        fromServer.close();
        clientInput.close();
        socket.close();
    }
}
