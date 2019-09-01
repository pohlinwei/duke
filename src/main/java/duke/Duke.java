package duke;

import duke.exception.InvalidCommandException;
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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.util.Optional;

import duke.task.TaskList;

import duke.command.Command;

import duke.util.Storage;
import duke.util.Ui;
import duke.util.Parser;

public class Duke {
    private static String entries = "../data/entries.txt";
    private TaskList taskList = TaskList.instanceOf();
    private Optional<Storage> storage;

    public Duke() {
        try {
            storage = Optional.of(new Storage(entries));
        } catch (Exception e) {
            // todo how to show this to user?
            Ui.showError(e);
        }
        Optional<Storage> desiredStorage = storage;
        desiredStorage.ifPresent(s -> taskList.addPreviousTasks(s.load()));
    }


    public void main(String[] args) {
        // todo how to greet user
        // Ui.sayHi();
    }

    public String getResponse(String input) {
        String response = "";
        Optional<Command> command;

        try {
            command = Parser.parse(input);
            response = command.get().execute(taskList, storage);
        } catch (Exception e) {
            response = Ui.showError(e);
        } finally {
            return response;
        }
    }
}