package duke.parser;

import duke.command.Command;

import duke.command.task.AddCommand;
import duke.command.task.DeleteCommand;
import duke.command.task.FindCommand;
import duke.command.task.ListCommand;
import duke.command.task.MarkDoneCommand;
import duke.command.task.TaskCmdType;
import duke.exception.DukeParseException;
import duke.exception.ExtraArgException;
import duke.exception.task.DeadlineParseException;
import duke.exception.task.DeleteParseException;
import duke.exception.task.EmptyDescriptionException;
import duke.exception.task.EventParseException;
import duke.exception.task.MarkDoneParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.DateFormatter;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Optional;

/**
 * Task parser parses input for task commands.
 */
class TaskParser {
    private static final int NUM_ARG_DEADLINE = 2;
    private static final int NUM_ARG_EVENT = 2;

    private static final String SEPARATOR_DEADLINE = " /by ";
    private static final String SEPARATOR_EVENT = " /at ";
    private static final String SEPARATOR_TIME = "-";

    private TaskParser() {

    }

    /**
     * Parses the input for a task command.
     *
     * @param taskCmdType type of task command
     * @param details details of command
     * @return command that allows the specified <code>taskCmdType</code> to be executed, if parsing is successful
     * @throws DukeParseException if details of command cannot be parsed successfully
     */
    static Optional<Command> parse(TaskCmdType taskCmdType, String details) throws DukeParseException {
        Optional<Command> command;

        switch (taskCmdType) {
        case DELETE:
            return Optional.of(parseDelete(details));
        case DONE:
            return Optional.of(parseDone(details));
        case LIST:
            return Optional.of(parseList(details));
        case FIND:
            return Optional.of(parseFind(details));
        case DEADLINE:
            Command addCommand = new AddCommand(parseDeadline(details));
            command = Optional.of(addCommand);
            break;
        case EVENT:
            Command addCommand1 = new AddCommand(parseEvent(details));
            command = Optional.of(addCommand1);
            break;
        case TODO:
            Command addCommand2 = new AddCommand(parseTodo(details));
            command = Optional.of(addCommand2);
            break;
        default:
            assert false : "All task commands should be valid type";
            return Optional.empty();
        }

        return command;
    }

    /**
     * Parses a delete command.
     *
     * @param details details of delete command
     * @return delete command
     * @throws DeleteParseException if parsing of details is unsuccessful
     */
    private static Command parseDelete(String details) throws DeleteParseException {
        try {
            int taskId = Integer.parseInt(details) - 1;
            return new DeleteCommand(taskId);
        } catch (NumberFormatException e) {
            throw new DeleteParseException();
        }
    }

    /**
     * Parses a mark done command.
     *
     * @param details details of mark done command
     * @return mark done command
     * @throws MarkDoneParseException if parsing of details is unsuccessful
     */
    private static Command parseDone(String details) throws MarkDoneParseException {
        try {
            int taskId = Integer.parseInt(details) - 1;
            return new MarkDoneCommand(taskId);
        } catch (NumberFormatException e) {
            throw new MarkDoneParseException();
        }
    }

    /**
     * Parses a list command.
     *
     * @param details details of list command, which should be an empty string
     * @return list command
     * @throws ExtraArgException if details contain extra arguments
     */
    private static Command parseList(String details) throws ExtraArgException {
        if (!details.equals("")) {
            throw new ExtraArgException("List command has extra argument. To list tasks, just type 'list'.");
        }

        return new ListCommand();
    }

    /**
     * Parses a find command.
     *
     * @param details details of find command
     * @return find command
     */
    private static Command parseFind(String details) {
        return new FindCommand(details);
    }

    /**
     * Parses a deadline command.
     *
     * @param details details of deadline command
     * @return delete command
     * @throws DukeParseException if details cannot be parsed successfully
     */
    private static Task parseDeadline(String details) throws DukeParseException {
        // required format: <taskDetails> /by <day/Month HH:mm>
        if (details.equals("")) {
            throw new EmptyDescriptionException("deadline");
        }

        boolean hasSeparator = details.contains(SEPARATOR_DEADLINE);
        if (!hasSeparator) {
            String errMsg = String.format("Missing '%s' between deadline info and date/time", SEPARATOR_DEADLINE);
            throw new DeadlineParseException(errMsg);
        }

        String[] parsedDetails = details.split(SEPARATOR_DEADLINE);
        if (parsedDetails.length != NUM_ARG_DEADLINE) {
            throw new DeadlineParseException("Missing deadline info or date/time info, or extra fields");
        }

        String info = parsedDetails[0];
        String dateTimeStr = parsedDetails[1];

        try {
            Calendar dateTimeInfo = DateFormatter.parseDate(dateTimeStr);
            return new Deadline(info, dateTimeStr, dateTimeInfo);
        } catch (ParseException e) {
            throw new DeadlineParseException("Incorrect date/time format for deadline");
        }
    }

    /**
     * Parses an event command.
     *
     * @param details details of event command
     * @return event command
     * @throws DukeParseException if details cannot be parsed successfully
     */
    private static Task parseEvent(String details) throws DukeParseException {
        // required format: <taskDetails> /at <day/Month HH:mm-HH:mm>
        if (details.equals("")) {
            throw new EmptyDescriptionException("event");
        }

        boolean hasSeparator = details.contains(SEPARATOR_EVENT);
        if (!hasSeparator) {
            String errMsg = String.format("Missing '%s' between event info and date/time", SEPARATOR_EVENT);
            throw new EventParseException(errMsg);
        }

        String[] parsedDetails = details.split(SEPARATOR_EVENT);
        if (parsedDetails.length != NUM_ARG_EVENT) {
            throw new EventParseException("Missing event info or date/time info, or extra fields");
        }

        String info = parsedDetails[0];
        String dateTimeStr = parsedDetails[1];

        String[] dateTimeArr = dateTimeStr.split(" ");

        boolean hasStartEndTime = dateTimeArr.length == 2;

        if (!hasStartEndTime) {
            throw new EventParseException("Missing date or time");
        }

        try {
            String date = dateTimeArr[0];
            String[] times = dateTimeArr[1].split(SEPARATOR_TIME);
            if (times.length != 2) {
                throw new ParseException("", 0);
            }
            Calendar startTime = DateFormatter.parseDate(date + " " + times[0]);
            Calendar endTime = DateFormatter.parseDate(date + " " + times[1]);
            return new Event(info, dateTimeStr, startTime, endTime);
        } catch (ParseException e) {
            throw new EventParseException("Incorrect date/time format for deadline");
        }
    }

    /**
     * Parses a todo command.
     *
     * @param details details of todo command
     * @return todo command
     * @throws EmptyDescriptionException if details is empty
     */
    private static Todo parseTodo(String details) throws EmptyDescriptionException {
        if (details.equals("")) {
            throw new EmptyDescriptionException("todo");
        }

        return new Todo(details);
    }
}
