import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Integer socketNr = 6896;
        try {
            serverSocket = new ServerSocket(socketNr);
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + socketNr.toString());
            System.exit(-1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: " + socketNr.toString());
            System.exit(-1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            out.println(inputLine);
            System.out.println(inputLine);
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();

    }
}