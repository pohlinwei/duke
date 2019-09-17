package duke.exception;

import java.io.IOException;

/**
 * Thrown to indicate that the specified file cannot be read or written to successfully.
 */
public class LoadException extends IOException {
    @Override
    public String getMessage() {
        return String.format("Unable to read/write tasks to hard disk.\nEntries will not be saved.");
    }
}
