package duke.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Optional;
import java.util.stream.IntStream;

import duke.command.ChangeStorageCommand;
import duke.command.Command;
import duke.command.AddCommand;
import duke.command.CommandType;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;

import duke.exception.DeadlineParseException;
import duke.exception.DeleteException;
import duke.exception.EmptyDescriptionException;
import duke.exception.EventParseException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidPathException;
import duke.exception.MarkDoneException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.Task;

/**
 * This class provides a parser which can be used to parse user input.
 */
public class Parser {
    // prevents user from directly creating a parser object
    private Parser() {}

    /**
     * Returns an <code>Optional</code> value. If <code>command</code> can be successfully parsed,
     * the result will contain the requested command; otherwise, it will be <code>empty</code>.
     *
     * <p>Command can be executed by using the <code>execute</code> method.
     *
     * @param command user input
     * @return <code>Optional</code>command as specified by user, if parsing is successful; otherwise it is empty
     */
    public static Optional<Command> parse(String command) throws Exception {
        String[] parsedBySpaceArgs = command.split(" ");
        if (parsedBySpaceArgs.length < 1) {
            throw new InvalidCommandException();
        }
        String commandVerb = parsedBySpaceArgs[0].toUpperCase();
        CommandType commandType;

        try {
            commandType = CommandType.valueOf(commandVerb);
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }

        switch (commandType) {
        case BYE:
            return Optional.of(new ExitCommand());
        case DELETE:
            if (parsedBySpaceArgs.length != 2) {
                throw new DeleteException();
            }
            int deletedTaskNum = Integer.parseInt(parsedBySpaceArgs[1]) - 1;
            return Optional.of(new DeleteCommand(deletedTaskNum));
        case DONE:
            if (parsedBySpaceArgs.length != 2) {
                throw new MarkDoneException();
            }
            int completedTaskNum = Integer.parseInt(parsedBySpaceArgs[1]) - 1;
            return Optional.of(new MarkDoneCommand(completedTaskNum));
        case LIST:
            return Optional.of(new ListCommand());
        case FIND:
            String query = IntStream.range(0, parsedBySpaceArgs.length)
                .mapToObj(i -> parsedBySpaceArgs[i])
                .reduce("", (prev, curr) -> prev + curr + " ")
                .trim();
            return Optional.of(new FindCommand(query));
        case STORAGE:
            if (parsedBySpaceArgs.length != 2) {
                throw new InvalidPathException();
            }
            String path = parsedBySpaceArgs[1];
            return Optional.of(new ChangeStorageCommand(path));
        default:
            try {
                if (parsedBySpaceArgs.length < 2) {
                    throw new EmptyDescriptionException(commandType.toString().toLowerCase());
                }
                String taskDetails = "";
                taskDetails = IntStream.range(1, parsedBySpaceArgs.length)
                        .mapToObj(i -> parsedBySpaceArgs[i])
                        .reduce("", (prev, curr) -> prev + curr + " ")
                        .trim();
                Task newTask = parseTask(commandType, taskDetails);
                return Optional.of(new AddCommand(newTask));
            } catch (ParseException e) {
                throw e;
            }
        }
    }

    /**
     * Parses <code>taskDetails</code> of tasks and creates a new object. The object will be either a
     * deadline, event or todo, as determined by <code>taskType</code>.
     *
     * @param taskType command which indicates type of task
     * @param taskDetails name, date and time of task
     * @return task with properties that are determined by command
     * @throws ParseException if <code>taskDetails</code> is in the incorrect format
     */
    private static Task parseTask(CommandType taskType, String taskDetails) throws ParseException {
        if (taskType.equals(CommandType.TODO)) {
            return parseTodo(taskDetails);
        } else if (taskType.equals(CommandType.DEADLINE)) {
            return parseDeadline(taskDetails);
        } else {
            assert taskType.equals(CommandType.EVENT) : "Only task of todo, deadline or event type can be parsed";
            return parseEvent(taskDetails);
        }
    }

    private static Task parseTodo(String taskDetails) {
        return new Todo(taskDetails);
    }

    private static Task parseDeadline(String taskDetails) throws ParseException {
        String[] parsedDetails = taskDetails.split(" /by ");
        if (parsedDetails.length != 2) {
            throw new DeadlineParseException("Missing '/by ' between name and date/time");
        }
        String name = parsedDetails[0];
        String dateTimeStr = parsedDetails[1];
        try {
            Calendar dateTimeInfo = DateFormatter.parseDate(dateTimeStr);
            return new Deadline(name, dateTimeStr, dateTimeInfo);
        } catch (ParseException e) {
            throw new DeadlineParseException("Incorrect date/time format for deadline");
        }
    }

    private static Task parseEvent(String taskDetails) throws ParseException {
        String[] parsedDetails = taskDetails.split(" /at ");
        if (parsedDetails.length != 2) {
            throw new EventParseException("Missing '/at ' between name and date/time");
        }
        String name = parsedDetails[0];
        String dateTimeInfo = parsedDetails[1];
        String[] dateTimeInfoArr = parsedDetails[1].split(" ");
        if (dateTimeInfoArr.length < 2) {
            throw new EventParseException("Incomplete date/time info");
        }
        String date = dateTimeInfoArr[0];
        String[] times = dateTimeInfoArr[1].split("-");
        if (times.length < 2) {
            throw new EventParseException("Missing start/end time");
        }
        try {
            Calendar startTime = DateFormatter.parseDate(date + " " + times[0]);
            Calendar endTime = DateFormatter.parseDate(date + " " + times[1]);
            return new Event(name, dateTimeInfo, startTime, endTime);
        } catch (ParseException e) {
            throw new EventParseException("Incorrect date/time format for deadline");
        }
    }
}