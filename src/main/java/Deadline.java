import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Deadline extends Task {
    private int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("d/M/y HHmm");

    private Date date;

    public Deadline(String taskName) throws DeadlineParseException {
        super(taskName);
        String dateAndTime = taskName.split(" /by: ")[1];
        String[] dateAndTimeArr = dateAndTime.split(" ");
        if (dateAndTimeArr.length != 2) {
            throw new DeadlineParseException("Missing date or time information");
        }
        String date = dateAndTimeArr[0];
        String times = dateAndTimeArr[1];

        try {
            this.date = dateFormatter.parse(date + "/" + currentYear + " " + times);
        } catch (ParseException e) {
            throw new DeadlineParseException("Incorrect formatting of deadline's date and time info");
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
            return String.format("[D][\u2714] %s (by: %s)", name, time);
        } else {
            return String.format("[D][\u2718] %s (by: %s)", name, time);
        }
    }
}