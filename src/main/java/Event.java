import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class Event extends Task {
    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("d/M/y HHmm");

    private Date start;
    private Date end;

    public Event(String taskName) throws EventParseException {
        super(taskName);
        String dateAndTime = taskName.split(" /at: ")[1];
        String[] dateAndTimeArr = dateAndTime.split(" ");
        if (dateAndTimeArr.length != 2) {
            throw new EventParseException("Missing date or time info");
        }
        String date = dateAndTimeArr[0];
        String[] times = dateAndTimeArr[1].split("-");
        if (times.length != 2) {
            throw new EventParseException("Missing hyphen between start and end time");
        }
        String startTime = times[0];
        String endTime = times[1];
        try {
            this.start = dateFormatter.parse(date + "/" + currentYear + " " + startTime);
            this.end = dateFormatter.parse(date + "/" + currentYear + " " + endTime);
        } catch (ParseException e) {
            throw new EventParseException("Incorrect formatting of event's date and time info");
        }
    }

    @Override
    public String toString() {
        String[] parsedName = taskName.split(" /");
        String name = parsedName[0];
        String[] info = parsedName[1].split(" ");
        String time = "";

        for (int i = 1; i < info.length; i++) {
            time += (" " + info[i]);
        }
        time = time.trim();

        if (done) {
            return String.format("[E][\u2714] %s (at: %s)", name, time);
        } else {
            return String.format("[E][\u2718] %s (at: %s)", name, time);
        }
    }
}