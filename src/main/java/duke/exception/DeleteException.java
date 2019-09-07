package duke.exception;

public class DeleteException extends IndexOutOfBoundsException {
    @Override
    public String getMessage() {
        return "Incorrect format for delete command. Please input 'delete <task number>'.";
    }
}
