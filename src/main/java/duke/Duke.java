package duke;

import java.util.Optional;

import duke.command.Command;
import duke.exception.LoadException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Parser;
import duke.util.Response;
import duke.util.Ui;

/**
 * This class returns Duke, a task manager.
 */

public class Duke {
    private static String entries = "../data/entries.txt";
    private TaskList taskList = TaskList.instanceOf();
    private Optional<Storage> storage;
    private boolean hasStorage = true;

    /**
     * Returns Duke, a task manager.
     * @throws LoadException if the specified file for storing user's tasks cannot be accessed, read or written to
     */
    public Duke() {
        try {
            storage = Optional.of(new Storage(entries));
        } catch (LoadException e) {
            storage = Optional.empty();
            this.hasStorage = false;
        }
        Optional<Storage> desiredStorage = storage;
        desiredStorage.ifPresent(s -> taskList.addPreviousTasks(s.load()));
    }

    /**
     * Executes the command implied by <code>input</code>. If the command is valid, the command will be
     * executed and the response message/outcome will be returned. If otherwise, the relevant error message
     * will be returned instead.
     * @param input command which determines the desired action to take
     * @return if <code>input</code> is a valid command, response message will be returned. If otherwise,
     * the error message will be returned
     */
    public Response getResponse(String input) {
        Response response = new Response();
        Optional<Command> command;

        try {
            command = Parser.parse(input);
            response = new Response(command.get().execute(taskList, storage), command.get().isExit());
        } catch (Exception e) {
            response = new Response(Ui.showError(e), false);
        } finally {
            return response;
        }
    }

    /**
     * Greets the user who has just logged on.
     * @return a message to greet user
     */
    public String sayHi() {
        return Ui.sayHi();
    }

    /**
     * Returns whether tasks can be saved to a local file.
     * @ return whether tasks can be saved to a local file
     */
    public boolean hasStorage() {
        return hasStorage;
    }
}