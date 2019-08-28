import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Event extends Task {
    private static SimpleDateFormat dateBeautifier = new SimpleDateFormat("dd/MM/yy h:mma");

    private String details;
    private Calendar startTime;
    private Calendar endTime;

    public Event(String taskName, String details, Calendar startTime, Calendar endTime) {
        super(taskName);
        this.details = details;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getInfo() {
        return String.format("E | %d | %s | %s", done ? 1 : 0, taskName, details);
    }

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

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s-%s)", done ? SYMBOL_COMPLETE : SYMBOL_INCOMPLETE, taskName,
                dateBeautifier.format(startTime.getTime()),
                dateBeautifier.format(endTime.getTime()).split(" ")[1]);
    }
}