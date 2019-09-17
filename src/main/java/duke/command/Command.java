package duke.command;


import duke.Manager;
import duke.command.task.TaskCmdType;
import duke.command.trivia.TriviaCmdType;
import duke.exception.DukeException;
import duke.util.storage.OptionalStorage;

/**
 * This allows commands, as indicated by the input, to be executed.
 */
public abstract class Command {
    /**
     * Executes the intended command.
     *
     * @param manager manager containing all inputs
     * @param storage storage which stores all inputs on the local hard disk, if any
     * @throws DukeException if command cannot be executed
     */
    public abstract String execute(Manager manager, OptionalStorage storage) throws DukeException;

    /**
     * Gets command type of <code>this</code>.
     *
     * @return command type of <code>this</code> command
     */
    public abstract CommandType getCommandType();

    /**
     * Gets command type of a specified input.
     *
     * @param userInput input from user
     * @return returns command type of the input
     */
    public static CommandType getCmdType(String userInput) {
        String[] parsedArgs = userInput.split(" ");
        String userCommand = parsedArgs[0].toUpperCase();

        boolean isTaskCmd = isTaskCmd(userCommand);
        if (isTaskCmd) {
            return CommandType.TASK;
        }

        boolean isExitCmd = isExitCmd(userCommand);
        if (isExitCmd) {
            return CommandType.BYE;
        }

        boolean isStoreCmd = isStoreCmd(userCommand);
        if (isStoreCmd) {
            return CommandType.STORE;
        }

        boolean isTriviaCmd = isTriviaCmd(userCommand);
        if (isTriviaCmd) {
            return CommandType.TRIVIA;
        }

        return CommandType.INVALID;
    }

    /**
     * Returns <code>true</code> if the specified command is a task command.
     *
     * @param userCommand command from user
     * @return <code>true</code> if the command is a task command
     */
    private static boolean isTaskCmd(String userCommand) {
        try {
            TaskCmdType.valueOf(userCommand);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Returns <code>true</code> if the specified command is a trivia command.
     *
     * @param userCommand command from user
     * @return <code>true</code> if the command is a trivia command
     */
    private static boolean isTriviaCmd(String userCommand) {
        try {
            TriviaCmdType.valueOf(userCommand);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Returns <code>true</code> if the specified command is an exit command.
     *
     * @param userCommand command from user
     * @return <code>true</code> if the command is an exit command
     */
    private static boolean isExitCmd(String userCommand) {
        try {
            CommandType commandType = CommandType.valueOf(userCommand);
            return commandType.equals(CommandType.BYE);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Returns <code>true</code> if the specified command is a store command.
     *
     * @param userCommand command from user
     * @return <code>true</code> if the command is a a store command
     */
    private static boolean isStoreCmd(String userCommand) {
        try {
            CommandType commandType = CommandType.valueOf(userCommand);
            return commandType.equals(CommandType.STORE);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

