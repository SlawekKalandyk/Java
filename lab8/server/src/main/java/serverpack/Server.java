package serverpack;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private void checkCommand(String command, PrintWriter printWriter, ArrayList<User> users) {
        String loginPattern = "^LOGIN (\\S*);(\\S*)$";
        String logoutPattern = "^LOGOUT (\\d{10})$";
        String lsPattern = "^LS (\\d{10})$";
        String getPattern = "^GET (\\d{10}) (\\S*)$";

        if (Pattern.matches(loginPattern, command)) {
            Pattern p = Pattern.compile(loginPattern);
            Matcher m = p.matcher(command);
            m.matches();

            ArrayList<String> loginFileInfo = FileReadAndWrite.read("serverfiles/loginInfo");
            String login = loginFileInfo.get(0).split(";", 2)[0];
            String password = loginFileInfo.get(0).split(";", 2)[1];

            if (login.equals(m.group(1)) && password.equals(m.group(2))) {
                String uniqueId = GenerateUniqueID.generate();
                users.add(new User(uniqueId));
                printWriter.println(uniqueId);
            } else {
                printWriter.println(levenshtein(password, m.group(2)));
            }
        } else if (Pattern.matches(logoutPattern, command)) {
            Pattern p = Pattern.compile(logoutPattern);
            Matcher m = p.matcher(command);
            m.matches();
            boolean flag = false;

            for (User user : users) {
                if (m.group(1).equals(user.getUniqueId())) {
                    users.remove(user);
                    printWriter.println("true");
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                printWriter.println("false");
            }
        } else if (Pattern.matches(lsPattern, command)) {
            Pattern p = Pattern.compile(lsPattern);
            Matcher m = p.matcher(command);
            m.matches();
            boolean flag = false;

            for (User user : users) {
                if (m.group(1).equals(user.getUniqueId())) {
                    File[] files = new File("serverfiles").listFiles();
                    if (files == null)
                        break;
                    String fileList = "";
                    for (File file : files)
                        fileList += file.getName() + ";";
                    fileList = fileList.substring(0, fileList.length() - 1);
                    printWriter.println(fileList);
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                printWriter.println("false");
            }
        } else if (Pattern.matches(getPattern, command)) {
            Pattern p = Pattern.compile(getPattern);
            Matcher m = p.matcher(command);
            m.matches();
            boolean flag = false;

            for (User user : users) {
                if (m.group(1).equals(user.getUniqueId())) {
                    File file = new File("serverfiles/" + m.group(2));
                    if (file.exists()) {
                        String line = FileReadAndWrite.read("serverfiles/" + m.group(2)).get(0);
                        printWriter.println(line);
                        flag = true;
                        break;
                    }
                }
            }

            if (!flag) {
                printWriter.println("false");
            }
        } else
            printWriter.println("Wrong input");
    }

    private Integer levenshtein(String password, String passwordGuess) {
        int distance[][] = new int[password.length() + 1][passwordGuess.length() + 1];

        for(int i = 0; i <= password.length(); ++i)
            distance[i][0] = i;

        for(int j = 1; j <= passwordGuess.length(); ++j)
            distance[0][j] = j;

        for(int i = 1; i <= password.length(); ++i) {
            for(int j = 1; j <= passwordGuess.length(); ++j) {
                int cost;
                if(password.charAt(i - 1) == passwordGuess.charAt(j - 1))
                    cost = 0;
                else
                    cost = 1;
                distance[i][j] = Math.min(Math.min(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1),
                        distance[i - 1][j - 1] + cost);
            }
        }

        return distance[password.length()][passwordGuess.length()];
    }
}
