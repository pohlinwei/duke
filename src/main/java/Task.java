import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

abstract public class Task {
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("d/M HHmm");
    protected static final String SYMBOL_COMPLETE = "\u2714";
    protected static final String SYMBOL_INCOMPLETE = "\u2718";


    protected String taskName;
    protected boolean done = false; // by default it's false

    public Task(String taskName) {
        this.taskName = taskName;
    }

    // returns a summary of the task's state and info
    abstract public String getInfo();

    public static Task strToTask(String taskInfo) {
        String[] parsedInfo = taskInfo.split(" \\| ");
        String type = parsedInfo[0];
        boolean done = Integer.parseInt(parsedInfo[1]) == 1;
        String name = parsedInfo[2];
        Task task;
        String time;
        switch (type) {
        case "T":
            task = Todo.stringToTask(name);
            break;
        case "D":
            time = parsedInfo[3];
            task = Deadline.stringToTask(name, time);
            break;
        default:
            time = parsedInfo[3];
            task = Event.stringToTask(name, time);
            break;
        }
        if (done) {
            task.setDone();
        }
        return task;
    }

    protected static Calendar parseDate(String str) {
        Calendar c = Calendar.getInstance();
        try {
            Date date = dateFormatter.parse(str);
            c.setTime(date);
            c.set(Calendar.YEAR, CURRENT_YEAR);
        } catch (ParseException e) {
            // won't fall into this block since this function is only used when str is valid
        } finally {
            return c;
        }
    }

    public void setDone() {
        done = true;
    }

    public boolean isDone() {
        return done;
    }
}

