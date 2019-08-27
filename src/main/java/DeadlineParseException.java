import java.text.ParseException;

public class DeadlineParseException extends ParseException {
    public DeadlineParseException(String str) {
        super(str, 0);
    }

    @Override
    public String getMessage() {
        return String.format(super.getMessage() + "\n" +
                "Please input time and date info for deadline in the following format:\n" +
                "d/m hhmm where hh:mm is in 24h format (e.g. 20/1 1800)");
    }
}
