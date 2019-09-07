package duke.exception;

public class InvalidPathException extends Exception {
    @Override
    public String getMessage() {
        return "Please indicate a valid path where your data should be stored.";
    }
}
