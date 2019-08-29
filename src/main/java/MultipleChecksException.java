/**
 * This indicates that the task with <code>taskNum</code> has previously been marked as done.
 */

class MultipleChecksException extends Exception {
    private int taskNum;

    /**
     * Returns an exception that indicates <code>task</code> with <code>taskNum</code> has previously been completed.
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