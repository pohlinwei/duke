package duke;

import java.io.IOException;

import duke.util.ui.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Duke duke = new Duke();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add("/stylesheets/dialogbox.css");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            fxmlLoader.<MainWindow>getController().informStorageCapabilities();
            fxmlLoader.<MainWindow>getController().greetUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
