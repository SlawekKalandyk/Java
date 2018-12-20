package clientpack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Controller {
    @FXML
    public Button connectButton;
    @FXML
    public TextField clientInput;
    @FXML
    public Button enterButton;
    @FXML
    public TextArea chatArea;

    private String host = "localhost";
    private Integer port = 6896;
    private Socket socket = null;

    public Controller() {
    }

    public void initialize() {
        chatArea.setText("Login is java2018.\n");
    }

    /*
        there's a delay between clicking the connect button and cantConnectMessage, why?????
     */
    public void onConnectButtonClick(ActionEvent actionEvent) {
        String cantConnectMessage = "Can't connect to server.\n";

        try {
            socket = new Socket(host, port);
        } catch (UnknownHostException uhEx) {
            chatArea.setText(chatArea.getText() + cantConnectMessage);
            return;
        } catch (IOException ioEx) {
            chatArea.setText(chatArea.getText() + cantConnectMessage);
            return;
        }
        String connectedMessage = "Connected to server.\n";
        chatArea.setText(chatArea.getText() + connectedMessage);
        connectButton.setDisable(true);
    }

    public void onClientInputEnter(ActionEvent actionEvent) {

        if (socket == null || socket.isClosed()) {
            String notConnected = "You are not connected to server!";
            chatArea.setText(chatArea.getText() + notConnected + "\n");
            clientInput.clear();
            return;
        }
        PrintWriter toServer = null;
        BufferedReader fromServer = null;

        try {
            toServer = new PrintWriter(socket.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ioEx) {

        }
        String clientMessage = clientInput.getText();
        toServer.println(clientMessage);
        chatArea.setText(chatArea.getText() + "[Client]: " + clientMessage + "\n");
        String serverAnswer;
        try {
            serverAnswer = fromServer.readLine();
        } catch (IOException ioEx) {
            serverAnswer = "Can't process server answer";
        }

        chatArea.setText(chatArea.getText() + "[Server]: " + serverAnswer + "\n");

        clientInput.clear();
        toServer.close();
        try {
            fromServer.close();
        } catch(IOException ioEx) {}

        connectionClose();
        connectButton.setDisable(false);
    }

    public void onEnterButtonClick(ActionEvent actionEvent) {
        onClientInputEnter(null); // argument doesn't matter in this case anyway
    }

    private void connectionClose() {
        try {
            socket.close();
        } catch (IOException ioEx) {
        }
    }
}
