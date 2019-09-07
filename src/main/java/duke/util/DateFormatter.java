package duke.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 */
public class DateFormatter {
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("d/M HHmm");

    // prevents user from directly creating a DateFormatter object
    private DateFormatter() {}

    public static Calendar parseDate(String str) throws ParseException {
        Calendar c = Calendar.getInstance();
        Date date = dateFormatter.parse(str);
        c.setTime(date);
        c.set(Calendar.YEAR, CURRENT_YEAR);
        return c;
    }


    public static Calendar parseCorrectedDate(String str) {
        Calendar c = Calendar.getInstance();
        try {
            Date date = dateFormatter.parse(str);
            c.setTime(date);
            c.set(Calendar.YEAR, CURRENT_YEAR);
        } catch (ParseException e) {
            assert false : "Date format was mistakenly accepted as correct";
        } finally {
            return c;
        }
    }

    public static SimpleDateFormat dateBeautifier() {
        return new SimpleDateFormat("dd/MM/yy h:mma");
    }
}
