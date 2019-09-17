package duke.exception.task;

import duke.exception.DukeException;

/**
 * Thrown to indicate that the task has previously been marked as complete and it cannot be marked again.
 */
public class MultipleChecksException extends DukeException {
    private int taskNum;

    /**
     * Constructs a <code>MultipleChecksException</code> to indicate that <code>task</code> with <code>taskNum</code>
     * has previously been completed.
     *
     * @param taskNum id of <code>task</code> in <code>taskList</code>
     */
    public MultipleChecksException(int taskNum) {
        super();
        this.taskNum = taskNum;
    }

    @Override
    public String getMessage() {
        return String.format("You have completed task %d earlier!", taskNum);
    }
}