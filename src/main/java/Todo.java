/**
 * This class represents tasks without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Returns a todo.
     *
     * @param taskName name or details of task
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Summarises the details and status of <code>this</code> task so that it can be stored.
     *
     * @return a summarised version of <code>this</code> task
     */
    public String getInfo() {
        return String.format("T | %d | %s", done ? 1 : 0, taskName);
    }

    /**
     * Returns an instance of <code>Task</code> representing a <code>Todo</code>
     * when given a string representation of it.
     *
     * @param name name of task
     * @return <code>Task</code> representing the given input
     */
    static Task stringToTask(String name) {
        return new Todo(name);
    }

    /**
     * Returns a formatted string version of <code>this</code>.
     *
     * @return formatted string version of <code>this</code>
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", done ? SYMBOL_COMPLETE : SYMBOL_INCOMPLETE, taskName);
    }

    /**
     * Checks whether <code>Object o</code> has the same <code>taskName</code> as <code>this</code>.
     *
     * @param o <code>Object</code> to be compared with <code>this</code>
     * @return boolean indicating whether the two objects are 'equal'
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            return this.taskName == (((Event) o).taskName);
        } else {
            return false;
        }
    }
}