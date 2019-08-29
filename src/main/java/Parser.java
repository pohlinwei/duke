import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * This class gives us a parser which we can use to parse user input.
 */
public class Parser {
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("d/M HHmm");

    /**
     * Returns an <code>Optional</code> value. If <code>command</code> can be successfully parsed,
     * the result will contain the requested command; otherwise, it will be <code>empty</code>.
     * <p>
     * Command can be executed by using the <code>execute</code> method.
     *
     * @param command user input
     * @return <code>Optional</code>command as specified by user, if parsing is successful; otherwise it is empty
     */
    public static Optional<Command> parse(String command) {
        try {
            String[] parsedBySpaceArgs = command.split(" ");
            if (parsedBySpaceArgs.length < 1 || parsedBySpaceArgs.length > 5) {
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
                int deletedTaskNum = Integer.parseInt(parsedBySpaceArgs[1]) - 1;
                return Optional.of(new DeleteCommand(deletedTaskNum));
            case DONE:
                int completedTaskNum = Integer.parseInt(parsedBySpaceArgs[1]) - 1;
                return Optional.of(new MarkDoneCommand(completedTaskNum));
            case LIST:
                return Optional.of(new ListCommand());
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
        } catch (Exception e) {
            Ui.showError(e);
            return Optional.empty();
        }
    }

    private static Task parseTask(CommandType commandType, String taskDetails) throws ParseException {
        if (commandType.equals(CommandType.TODO)) {
            return parseTodo(taskDetails);
        } else if (commandType.equals(CommandType.DEADLINE)) {
            return parseDeadline(taskDetails);
        } else {
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
            Calendar dateTimeInfo = parseDate(dateTimeStr);
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
            Calendar startTime = parseDate(date + " " + times[0]);
            Calendar endTime = parseDate(date + " " + times[1]);
            return new Event(name, dateTimeInfo, startTime, endTime);
        } catch (ParseException e) {
            throw new EventParseException("Incorrect date/time format for deadline");
        }
    }

    private static Calendar parseDate(String str) throws ParseException {
        Calendar c = Calendar.getInstance();
        Date date = dateFormatter.parse(str);
        c.setTime(date);
        c.set(Calendar.YEAR, CURRENT_YEAR);
        return c;
    }
}