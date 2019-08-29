import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This class represents tasks that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    private static SimpleDateFormat dateBeautifier = new SimpleDateFormat("dd/MM/yy h:mma");

    private String details;
    private Calendar startTime;
    private Calendar endTime;

    /**
     * Returns an event with a task name, start time and end time.
     * @param taskName task of name
     * @param details date, start time and end time of event in the format d/M hhmm-hhmm (24h format)
     * @param startTime formatted start time
     * @param endTime formatted end time
     */
    public Event(String taskName, String details, Calendar startTime, Calendar endTime) {
        super(taskName);
        this.details = details;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Summarises the details and status of <code>this</code> task so that it can be stored.
     *
     * @return a summarised version of <code>this</code> task
     */
    public String getInfo() {
        return String.format("E | %d | %s | %s", done ? 1 : 0, taskName, details);
    }

    /**
     * Returns an instance of <code>Task</code> representing an <code>Event</code>
     * when given a string representation of it.
     *
     * @param name name of task
     * @return <code>Task</code> representing the given input
     */
    static Task stringToTask(String name, String timeInfo) {
        String[] dateTimeArr = timeInfo.split(" ");
        String date = dateTimeArr[0];
        String[] times = dateTimeArr[1].split("-");
        String time1 = times[0];
        String time2 = times[1];
        String timeDate1 = date + " " + time1;
        String timeDate2 = date + " " + time2;
        return new Event(name, timeInfo, Task.parseDate(timeDate1), Task.parseDate(timeDate2));
    }

    /**
     * Returns a formatted string version of <code>this</code>.
     *
     * @return formatted string version of <code>this</code>
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s-%s)", done ? SYMBOL_COMPLETE : SYMBOL_INCOMPLETE, taskName,
                dateBeautifier.format(startTime.getTime()),
                dateBeautifier.format(endTime.getTime()).split(" ")[1]);
    }
}