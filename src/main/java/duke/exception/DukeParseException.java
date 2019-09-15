package duke.exception;

public class DukeParseException extends DukeException {
    protected String msg;

    public DukeParseException(String msg) {
        this.msg = msg;
    }

    public DukeParseException() {}

    @Override
    public String getMessage() {
        return msg;
    }
}
