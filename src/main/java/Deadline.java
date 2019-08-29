import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This class represents tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    private static SimpleDateFormat dateBeautifier = new SimpleDateFormat("dd/MM/yy h:mma");

    private String details;
    private Calendar dateTime;

    /**
     * Returns a task with a deadline field.
     *
     * @param taskName name of task
     * @param details latest completion date and time
     * @param dateTime formatted version of <code>details</code>
     */
    public Deadline(String taskName, String details, Calendar dateTime) {
        super(taskName);
        this.details = details;
        this.dateTime = dateTime;
    }

    /**
     * Summarises the details and status of <code>this</code> task so that it can be stored.
     *
     * @return a summarised version of <code>this</code> task
     */
    public String getInfo() {
        return String.format("D | %d | %s | %s", done ? 1 : 0, taskName, details);
    }

    /**
     * Returns an instance of <code>Task</code> representing a <code>Deadline</code>
     * when given a string representation of it.
     *
     * @param name name of task
     * @param timeInfo latest completion date and time
     * @return <code>Task</code> representing the given input
     */
    static Task stringToTask(String name, String timeInfo) {
        return new Deadline(name, timeInfo, Task.parseDate(timeInfo));
    }

    /**
     * Returns a formatted string version of <code>this</code>.
     *
     * @return formatted string version of <code>this</code>
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", done ? SYMBOL_COMPLETE : SYMBOL_INCOMPLETE,
                taskName, dateBeautifier.format(dateTime.getTime()));
    }

    /**
     * Checks whether <code>Object o</code> has the same <code>taskName</code> and <code>details</code> as
     * <code>this</code>.
     *
     * @param o <code>Object</code> to be compared with <code>this</code>
     * @return boolean indicating whether the two objects are 'equal'
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            return this.taskName == ((Deadline) o).taskName
                    && this.details == ((Deadline) o).details;
        } else {
            return false;
        }
    }
}