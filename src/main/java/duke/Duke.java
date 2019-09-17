package duke;

import java.util.Optional;

import duke.command.Command;
import duke.command.CommandType;
import duke.exception.DukeException;
import duke.exception.LoadException;
import duke.task.TaskManager;
import duke.trivia.TriviaManager;
import duke.util.storage.OptionalStorage;
import duke.parser.Parser;
import duke.util.ui.Ui;
import duke.util.Response;

/**
 * This class returns Duke, a task manager and a trivia manager.
 */

public class Duke {
    // for tasks
    private static String taskFile = "../data/tasks.txt";
    private TaskManager taskManager = new TaskManager();
    private OptionalStorage taskStorage;
    private boolean hasTaskStorage = true;

    // for trivia
    private static String triviaFile = "../data/trivia.txt";
    private TriviaManager triviaManager = new TriviaManager();
    private OptionalStorage triviaStorage;

    /**
     * Constructs <code>Duke</code>, a task manager and a trivia manager.
     * @throws LoadException if the specified file for storing user's tasks cannot be accessed, read or written to
     */
    public Duke() {
        try {
            taskStorage = new OptionalStorage(taskFile);
            taskStorage.load(taskManager);
        } catch (LoadException e) {
            taskStorage = new OptionalStorage();
            this.hasTaskStorage = false;
        } catch (ArrayIndexOutOfBoundsException e) {
            taskStorage = new OptionalStorage();
            this.hasTaskStorage = false;
        }

        try {
            triviaStorage = new OptionalStorage(triviaFile);
            triviaStorage.load(triviaManager);
        } catch (LoadException e) {
            triviaStorage = new OptionalStorage();
        } catch (ArrayIndexOutOfBoundsException e) {
            triviaStorage = new OptionalStorage();
        }
    }

    /**
     * Executes the command implied by <code>input</code>. If the command is valid, the command will be
     * executed and the response message/outcome will be returned. If otherwise, the relevant error message
     * will be returned instead.
     * @param input command which determines the desired action to take
     * @return if <code>input</code> is a valid command, response message will be returned. If otherwise,
     *     the error message will be returned
     */
    public Response getResponse(String input) {
        Response response = new Response();
        Optional<Command> command;

        try {
            command = Parser.parse(input);
            CommandType commandType = command.get().getCommandType();
            String message = "";
            if (commandType.equals(CommandType.TASK)) {
                message = command.get().execute(taskManager, taskStorage);
            } else if (commandType.equals(CommandType.TRIVIA)) {
                message = command.get().execute(triviaManager, triviaStorage);
            } else if (commandType.equals(CommandType.BYE)) {
                message = command.get().execute(taskManager, taskStorage);
            } else if (commandType.equals(CommandType.STORE)) {
                message = command.get().execute(taskManager, taskStorage);
            }
            assert !(message.equals("")) : "Message to user should not be empty";
            response = new Response(message, commandType);
        } catch (DukeException e) {
            response = new Response(Ui.showError(e), CommandType.INVALID);
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
     * Returns <code>true</code> if the the inputs can be stored locally.
     * @return <code>true</code> if the the inputs can be stored locally
     */
    public boolean hasTaskStorage() {
        return hasTaskStorage;
    }
}