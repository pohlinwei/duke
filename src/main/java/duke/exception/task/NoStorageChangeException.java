package duke.exception.task;

import duke.exception.DukeException;

/**
 * Thrown to indicate that the specified file cannot be read or written to successfully.
 */
public class NoStorageChangeException extends DukeException {
    private String path;

    /**
     * Constructs a <code>NoStorageChangeException</code> to indicate that the specified file cannot be read or
     * written to.
     * @param path path where the tasks are supposed to be stored locally
     */
    public NoStorageChangeException(String path) {
        this.path = path;
    }

    @Override
    public String getMessage() {
        return String.format("Unable to change storage file to that stored at %s", path);
    }
}
