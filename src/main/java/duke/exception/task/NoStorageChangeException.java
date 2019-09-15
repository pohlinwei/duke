package duke.exception.task;

import duke.exception.DukeException;

public class NoStorageChangeException extends DukeException {
    String path;

    public NoStorageChangeException(String path) {
        this.path = path;
    }

    @Override
    public String getMessage() {
        return String.format("Unable to change storage file to that stored at %s", path);
    }
}
