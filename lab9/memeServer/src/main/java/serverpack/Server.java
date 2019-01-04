package serverpack;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private DatabaseCommands databaseCommands = new DatabaseCommands();

    public void run(Integer port) throws IOException {
        String endCommand = "exit";

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
            System.out.println("AAAA");
            PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputFromClient = fromClient.readLine();
            checkCommand(inputFromClient, toClient);

            toClient.close();
            fromClient.close();
            //clientSocket.close();
        }
    }

    public void checkCommand(String inputFromClient, PrintWriter toClient) {
        String command = inputFromClient.split(" ")[0];
        System.out.println(inputFromClient);
        if(command.equals("login")) {
            Integer id = Integer.parseInt(inputFromClient.split(" ")[1].split(";")[0]);
            String password = inputFromClient.split(" ")[1].split(";")[1];
            databaseCommands.connect();
            toClient.println(databaseCommands.checkIfUserExistsInDatabase(id, password).toString());
        } else if(command.equals("register")) {
            Integer id = Integer.parseInt(inputFromClient.split(" ")[1].split(";")[0]);
            String password = inputFromClient.split(" ")[1].split(";")[1];
            databaseCommands.connect();
            toClient.println(databaseCommands.registerUser(id, password).toString());
        } else if(command.equals("getregisterid")) {
            databaseCommands.connect();
            toClient.println(databaseCommands.getRegisterId().toString());
        }
    }
}
