package duke.parser;

import java.util.Optional;

import duke.command.task.ChangeStorageCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.ExitCommand;
import duke.command.task.TaskCmdType;

import duke.command.trivia.TriviaCmdType;
import duke.exception.DukeParseException;
import duke.exception.InvalidCommandException;
import duke.exception.ExtraArgException;

import duke.exception.StoreParseException;

/**
 * This class provides a parser which can be used to parse user input.
 */
public class Parser {
    private static final int NUM_ARGS_STORE = 2; //todo change to 3

    // prevents user from directly creating a parser object
    private Parser() {}

    public static Optional<Command> parse(String userInput) throws InvalidCommandException, DukeParseException {
        CommandType cmdType = getCmdType(userInput);

        if (cmdType.equals(CommandType.TASK)) {
            return parseTaskCommand(userInput);
        } else if (cmdType.equals(CommandType.TRIVIA)) {
            return parseTriviaCommand(userInput);
        } else if (cmdType.equals(CommandType.BYE)) {
            return parseByeCommand(userInput);
        } else if (cmdType.equals(CommandType.STORE)) {
            return parseStoreCommand(userInput);
        } else if (cmdType.equals(CommandType.INVALID)) {
            throw new InvalidCommandException();
        } else {
            assert false : "Failed to catch all possible user command types";
            return Optional.empty();
        }
    }

    private static CommandType getCmdType(String userInput) {
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

    private static boolean isTaskCmd(String userCommand) {
        try {
            TaskCmdType.valueOf(userCommand);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean isTriviaCmd(String userCommand) {
        try {
            TriviaCmdType.valueOf(userCommand);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean isExitCmd(String userCommand) {
        try {
            CommandType commandType = CommandType.valueOf(userCommand);
            return commandType.equals(CommandType.BYE);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean isStoreCmd(String userCommand) {
        try {
            CommandType commandType = CommandType.valueOf(userCommand);
            return commandType.equals(CommandType.STORE);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static Optional<Command> parseTaskCommand(String userInput) throws DukeParseException {
        String userCmdString = userInput.split(" ")[0];
        TaskCmdType userCommand = TaskCmdType.valueOf(userCmdString.toUpperCase());
        String details = StringFormatter.excludeRegex(userCmdString, userInput);
        return TaskParser.parse(userCommand, details);
    }

    private static Optional<Command> parseTriviaCommand(String userInput) throws DukeParseException {
        String userCmdString = userInput.split(" ")[0];
        TriviaCmdType userCommand = TriviaCmdType.valueOf(userCmdString.toUpperCase());
        String details = StringFormatter.excludeRegex(userCmdString, userInput);
        return TriviaParser.parse(userCommand, details);
    }

    private static Optional<Command> parseByeCommand(String userInput) throws ExtraArgException {
        String[] userCmdString = userInput.split(" ");
        boolean hasExtraArg = userCmdString.length > 1;
        if (hasExtraArg) {
            throw new ExtraArgException("Bye command has extra argument. To exit, just type 'bye'.");
        }
        return Optional.of(new ExitCommand());
    }

    private static Optional<Command> parseStoreCommand(String userInput) throws StoreParseException {
        String[] parsedArgs = userInput.split(" ");
        if (parsedArgs.length != NUM_ARGS_STORE) {
            throw new StoreParseException();
        }
        String details = StringFormatter.excludeRegex(parsedArgs[0], userInput);

        return Optional.of(new ChangeStorageCommand(details));
    }

    /**
     * Returns an <code>Optional</code> value. If <code>command</code> can be successfully parsed,
     * the result will contain the requested command; otherwise, it will be <code>empty</code>.
     *
     * <p>Command can be executed by using the <code>execute</code> method.
     *
     * @param command user input
     * @return <code>Optional</code>command as specified by user, if parsing is successful; otherwise it is empty
     */


    /**
     * Parses <code>taskDetails</code> of tasks and creates a new object. The object will be either a
     * deadline, event or todo, as determined by <code>taskType</code>.
     *
     * @param taskType command which indicates type of task
     * @param taskDetails name, date and time of task
     * @return task with properties that are determined by command
     * @throws ParseException if <code>taskDetails</code> is in the incorrect format
     */
}