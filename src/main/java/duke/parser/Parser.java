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
 * Parser to parse input.
 */
public class Parser {
    private static final int NUM_ARGS_STORE = 2;

    // prevents user from directly creating a parser object
    private Parser() {}

    /**
     * Parses a given input.
     *
     * @param userInput input provided by the user
     * @return a command that can be executed, if <code>userInput</code> is valid
     * @throws InvalidCommandException if the command used is not recognised
     * @throws DukeParseException if the command is correct but the input cannot be parsed
     */
    public static Optional<Command> parse(String userInput) throws InvalidCommandException, DukeParseException {
        CommandType cmdType = Command.getCmdType(userInput);

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

    /**
     * Parses task command.
     *
     * @param userInput input from user
     * @return task command, if parsing is successful
     * @throws DukeParseException if input is unsuccessfully parsed
     */
    private static Optional<Command> parseTaskCommand(String userInput) throws DukeParseException {
        String userCmdString = userInput.split(" ")[0];
        TaskCmdType userCommand = TaskCmdType.valueOf(userCmdString.toUpperCase());
        String details = StringFormatter.excludeRegex(userCmdString, userInput);
        return TaskParser.parse(userCommand, details);
    }

    /**
     * Parses trivia command.
     *
     * @param userInput input from user
     * @return trivia command, if parsing is successful
     * @throws DukeParseException if input is unsuccessfully parsed
     */
    private static Optional<Command> parseTriviaCommand(String userInput) throws DukeParseException {
        String userCmdString = userInput.split(" ")[0];
        TriviaCmdType userCommand = TriviaCmdType.valueOf(userCmdString.toUpperCase());
        String details = StringFormatter.excludeRegex(userCmdString, userInput);
        return TriviaParser.parse(userCommand, details);
    }

    /**
     * Parses exit command.
     *
     * @param userInput input from user
     * @return exit command, if parsing is successful
     * @throws ExtraArgException if too many arguments are found
     */
    private static Optional<Command> parseByeCommand(String userInput) throws ExtraArgException {
        String[] userCmdString = userInput.split(" ");
        boolean hasExtraArg = userCmdString.length > 1;
        if (hasExtraArg) {
            throw new ExtraArgException("Bye command has extra argument. To exit, just type 'bye'.");
        }
        return Optional.of(new ExitCommand());
    }

    /**
     * Parses store command.
     *
     * @param userInput input from user
     * @return store command, if parsing is successful
     * @throws StoreParseException if input is unsuccessfully parsed
     */
    private static Optional<Command> parseStoreCommand(String userInput) throws StoreParseException {
        String[] parsedArgs = userInput.split(" ");
        if (parsedArgs.length != NUM_ARGS_STORE) {
            throw new StoreParseException();
        }
        String details = StringFormatter.excludeRegex(parsedArgs[0], userInput);

        return Optional.of(new ChangeStorageCommand(details));
    }
}