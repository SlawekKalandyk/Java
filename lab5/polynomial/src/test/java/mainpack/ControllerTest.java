package mainpack;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.matcher.control.TableViewMatchers.*;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.testfx.robot.Motion;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import org.junit.Test;
import javafx.fxml.FXMLLoader;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.*;

public class ControllerTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/GuiFile.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Polynomial Chart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*
        all begin/end X/Y axis fields are copies of each other when it comes to this,
        this is a test of functions similar to onBeginXAxisChanged(ActionEvent actionEvent) {...}
     */
    @Test
    public void onAxisValueChangeCheckIfTextFieldIsClearAndPromptTextSetTest() {
        clickOn("#beginXAxisInput");
        write("-10.0");
        push(KeyCode.ENTER);
        assertEquals("", lookup("#beginXAxisInput").<TextField>query().getText());
        assertEquals("Currently: -10.0", lookup("#beginXAxisInput").<TextField>query().getPromptText());
    }

    @Test
    public void checkIfChangingCoefficientsAmountCreatesFieldsInTable() {
        clickOn("#coefficientsAmountInput");
        write("3");
        push(KeyCode.ENTER);
        verifyThat("#coefficientsTableView", hasNumRows(3));
        verifyThat("#coefficientsTableView", hasTableCell("a0"));
        verifyThat("#coefficientsTableView", hasTableCell("a1"));
        verifyThat("#coefficientsTableView", hasTableCell("a2"));
    }
}
