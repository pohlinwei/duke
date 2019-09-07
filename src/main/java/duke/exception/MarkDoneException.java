package duke.exception;

public class MarkDoneException extends IndexOutOfBoundsException {
    @Override
    public String getMessage() {
        return "Incorrect format for mark done command. Please input 'done <task number>'.";
    }
}
