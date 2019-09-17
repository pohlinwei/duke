package duke.exception.task;

import duke.exception.DukeParseException;

/**
 * Thrown to indicate that task description is missing.
 */
public class EmptyDescriptionException extends DukeParseException {
    private String type;

    /**
     * Constructs an <code>EmptyDescriptionException</code> for a specific type of task.
     *
     * @param taskType type of task which has no description
     */
    public EmptyDescriptionException(String taskType) {
        super();
        if (taskType.equals("event")) {
            type = "an event";
        } else if (taskType.equals("todo")) {
            type = "a todo";
        } else if (taskType.equals("deadline")){
            type = "a deadline";
        } else {
            assert false : "Task type should be an event, todo or deadline";
        }
    }

    @Override
    public String getMessage() {
        return String.format("The description of %s cannot be empty.", type);
    }
}