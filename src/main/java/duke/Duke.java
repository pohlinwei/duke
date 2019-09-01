package duke;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Optional;
import java.util.Scanner;

import duke.task.TaskList;

import duke.command.Command;
import duke.command.ExitCommand;

import duke.util.Storage;
import duke.util.Ui;
import duke.util.Parser;

public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));

    private static String entries = "../data/entries.txt";

    public static void main(String[] args) {
        // set up
        TaskList taskList = TaskList.instanceOf();
        Optional<Storage> desiredStorage = Optional.empty();
        try {
            desiredStorage = Optional.of(new Storage(entries));
        } catch (Exception e) {
            Ui.showError(e);
        }
        Optional<Storage> storage = desiredStorage;
        storage.ifPresent(s -> taskList.addPreviousTasks(s.load()));

        // greet user
        Ui.sayHi();

        // get first user input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Optional<Command> currentCommand = Parser.parse(input);
        boolean isExitCommand = currentCommand.filter(c -> c instanceof ExitCommand).isPresent();

        while (!isExitCommand) {
            currentCommand.ifPresent(c -> {
                try {
                    c.execute(taskList, storage);
                } catch (Exception e) {
                    Ui.showError(e);
                }
            });

            input = sc.nextLine();
            currentCommand = Parser.parse(input);
            isExitCommand = currentCommand.filter(c -> c instanceof ExitCommand).isPresent();
        }

        Ui.sayBye();
    }

    @Override
    public void start(Stage stage) {
        // setup layout
        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));

        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        userInput.setPrefWidth(325.0);

        sendButton = new Button("Send");
        sendButton.setPrefWidth(55.0);

        AnchorPane mainLayout = new AnchorPane();
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        // add functionality to handle user input
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        stage.show();
    }

    private Label getDialogLabel(String txt) {
        Label textToAdd = new Label(txt);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}

class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);


        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}