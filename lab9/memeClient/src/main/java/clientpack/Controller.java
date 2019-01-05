package clientpack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;

public class Controller {
    @FXML
    public TextField startLoginIdField;
    @FXML
    public PasswordField startLoginPasswordField;
    @FXML
    public Button startLoginEnterButton;
    @FXML
    public TextField startRegisterIdField;
    @FXML
    public PasswordField startRegisterPasswordField;
    @FXML
    public Button startRegisterEnterButton;
    @FXML
    public Button addMemeClearButton;
    @FXML
    public TextField addMemeNameField;
    @FXML
    public TextField addMemeUpperTextField;
    @FXML
    public TextField addMemeLowerTextField;
    @FXML
    public Button addMemeAddButton;
    @FXML
    public Tab addMemeTab;
    @FXML
    public Tab browseMemesTab;
    @FXML
    public Button chooseFileFromDiskButton;
    @FXML
    public Button chooseFileFromTemplatesButton;

    private String host = "localhost";
    private Integer port = 6896;
    private Socket socket = null;

    public Controller() {

    }

    public void initialize() {
        connect();
        setAutomaticRegisterId();
    }

    public void connect() {
        try {
            socket = new Socket(host, port) {
                protected void finalize() throws IOException {
                    this.close();
                }
            };
        } catch (UnknownHostException uhEx) {
            uhEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    private void setAutomaticRegisterId() {
        PrintWriter toServer = null;
        BufferedReader fromServer = null;

        try {
            toServer = new PrintWriter(socket.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        toServer.println("getregisterid");

        String serverAnswer = null;
        try {
            serverAnswer = fromServer.readLine();
        } catch (IOException ioEx) {
        }

        startRegisterIdField.setText(serverAnswer);
        toServer.close();

        try {
            fromServer.close();
        } catch(IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void onStartLoginEnterButtonClick(ActionEvent actionEvent) {

        String loginId = startLoginIdField.getText();
        String loginPassword = startLoginPasswordField.getText();
        if (loginId.equals("") || loginPassword.equals(""))
            return;

        PrintWriter toServer = null;
        BufferedReader fromServer = null;

        try {
            toServer = new PrintWriter(socket.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        toServer.println("login " + loginId + ";" + loginPassword);

        String serverAnswer = null;
        try {
            serverAnswer = fromServer.readLine();
        } catch (IOException ioEx) {
        }

        /*
            TODO: login success/failure popup
         */
        if (Boolean.parseBoolean(serverAnswer)) {
            addMemeTab.setDisable(false);
            browseMemesTab.setDisable(false);
        } else {

        }

        startLoginIdField.clear();
        startLoginPasswordField.clear();
        toServer.close();
        try {
            fromServer.close();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void onStartRegisterEnterButtonClick(ActionEvent actionEvent) {
        String registerId = startRegisterIdField.getText();
        String registerPassword = startRegisterPasswordField.getText();

        if (registerId.equals("") || registerPassword.equals(""))
            return;

        PrintWriter toServer = null;
        BufferedReader fromServer = null;

        try {
            toServer = new PrintWriter(socket.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        toServer.println("register " + registerId + ";" + registerPassword);

        String serverAnswer = null;
        try {
            serverAnswer = fromServer.readLine();
        } catch (IOException ioEx) {
        }

        /*
            TODO: register success/failure popup
         */
        if (Boolean.parseBoolean(serverAnswer)) {

        } else {

        }

        setAutomaticRegisterId();
        startRegisterPasswordField.clear();
        toServer.close();
        try {
            fromServer.close();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void onAddMemeClearButtonClick(ActionEvent actionEvent) {

    }

    public void onAddMemeAddButtonClick(ActionEvent actionEvent) {

    }

    public void onChooseFileFromDiskButtonClick(ActionEvent actionEvent) {

    }

    public void onChooseFileFromTemplatesButtonClick(ActionEvent actionEvent) {

    }
}
