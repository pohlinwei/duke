import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Deadline extends Task {
    private static SimpleDateFormat dateBeautifier = new SimpleDateFormat("dd/MM/yy h:mma");

    private String details;
    private Calendar dateTime;

    public Deadline(String taskName, String details, Calendar dateTime) {
        super(taskName);
        this.details = details;
        this.dateTime = dateTime;
    }

    public String getInfo() {
        return String.format("D | %d | %s | %s", done ? 1 : 0, taskName, details);
    }

    static Task stringToTask(String name, String timeInfo) {
        return new Deadline(name, timeInfo, Task.parseDate(timeInfo));
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", done ? SYMBOL_COMPLETE : SYMBOL_INCOMPLETE,
                taskName, dateBeautifier.format(dateTime.getTime()));
    }

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