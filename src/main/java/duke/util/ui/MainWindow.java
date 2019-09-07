package duke.util.ui;

import duke.Duke;
import duke.exception.LoadException;
import duke.util.Response;
import duke.util.ui.DialogBox;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.getMessage(), dukeImage)
        );
        userInput.clear();
        if (response.isExit()) {
            disableInput();
        }
    }

    @FXML
    public void greetUser() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.sayHi(), dukeImage)
        );
    }

    @FXML
    public void informStorageCapabilities() {
        if (!duke.hasStorage()) {
            String errMsg = (new LoadException()).toString();
            dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(errMsg, dukeImage)
            );
        }
    }

    @FXML
    public void disableInput() {
        userInput.setDisable(true);
        sendButton.setDisable(true);
    }
}