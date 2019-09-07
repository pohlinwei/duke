package duke;

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

    public String sayHi() {
        return Ui.sayHi();
    }
}