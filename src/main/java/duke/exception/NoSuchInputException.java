package duke.exception;

public class NoSuchInputException extends DukeException {
    private String msg;

    public NoSuchInputException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}