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

import java.io.IOException;
import java.util.Collections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.util.Optional;
import java.util.Scanner;

import duke.task.TaskList;

import duke.command.Command;
import duke.command.ExitCommand;

import duke.util.Storage;
import duke.util.Ui;
import duke.util.Parser;

public class Duke {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

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

    String getResponse(String input) {
        return "Duke hears: " + input;
    }
}