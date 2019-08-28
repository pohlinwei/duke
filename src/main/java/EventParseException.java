import java.text.ParseException;

public class EventParseException extends ParseException {
    public EventParseException(String str) {
        super(str, 0 );
    }

    @Override
    public String getMessage() {
        return String.format(super.getMessage() + "\n" +
                "Please input event info in the following format:\n" +
                "\tevent dance /at 20/1 1800-1830");
    }
}
