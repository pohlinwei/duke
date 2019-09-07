package duke.exception;

import java.io.IOException;

public class LoadException extends IOException {
    public LoadException() {
        super();
    }

    @Override
    public String getMessage() {
        return String.format("Unable to read/write tasks to hard disk.\nEntries will not be saved.");
    }
}
