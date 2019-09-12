package duke.exception;

/**
 * This indicates that the user has indicated an empty description when trying to add a task.
 */
public class EmptyDescriptionException extends Exception {
    private String type;

    /**
     * Returns exception indicating that the user has indicated an empty description when trying to add a task.
     * @param task task which has no description
     */
    public EmptyDescriptionException(String task) {
        super();
        if (task.equals("event")) {
            type = "an event";
        } else if (task.equals("todo")) {
            type = "a todo";
        } else if (task.equals("deadline")){
            type = "a deadline";
        } else {
            assert false : "Task type should be an event/todo/deadline";
        }
    }

    @Override
    public String getMessage() {
        return String.format("The description of %s cannot be empty.", type);
    }
}