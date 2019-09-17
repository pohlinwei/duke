package duke.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date formatter which parses date and allows date to be formatted.
 */
public class DateFormatter {
    private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("d/M HHmm");

    // prevents user from directly creating a DateFormatter object
    private DateFormatter() {

    }

    /**
     * Parses date that is represented by a string and returns a <code>Calendar</code> object.
     * @param str date that is to be parsed
     * @return a <code>Calendar</code> object that represents the input date formally
     * @throws ParseException if the provided string cannot be parsed
     */
    public static Calendar parseDate(String str) throws ParseException {
        Calendar c = Calendar.getInstance();
        Date date = dateFormatter.parse(str);
        c.setTime(date);
        c.set(Calendar.YEAR, CURRENT_YEAR);
        return c;
    }

    /**
     * Parses date that is represented by a string and returns a <code>Calendar</code> object.
     * It is known that the input string can be parsed successfully.
     * @param str date that is to be parsed
     * @return a <code>Calendar</code> object that represents the input date formally.
     */
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

    /**
     * Returns a <code>SimpleDateFormat</code> that can be used to format date in the dd/MM/yy h:mm am/pm format.
     * @return a <code>SimpleDateFormat</code> that can be used to format date in the dd/MM/yy h:mm am/pm format
     */
    public static SimpleDateFormat dateBeautifier() {
        return new SimpleDateFormat("dd/MM/yy h:mma");
    }
}
