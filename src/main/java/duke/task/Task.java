package duke.task;

/**
 * An <code>abstract</code> class representing a task which has name and status (whether it is done) fields.
 */
public abstract class Task {
    protected static final String SYMBOL_COMPLETE = "\u2714";
    protected static final String SYMBOL_INCOMPLETE = "\u2718";


    protected String taskName;
    protected boolean isDone = false; // by default it's false

    /**
     * Returns a task with <code>taskName</code>.
     *
     * @param taskName name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Summarises the details and status of <code>this</code> task so that it can be stored.
     *
     * @return a summarised version of <code>this</code> task
     */
    public abstract String getInfo();

    /**
     * Converts a string represented task to an instance of a <code>Task</code>.
     *
     * @param taskInfo summarised string representation of task
     * @return an instance of <code>Task</code> with fields found in <code>taskInfo</code>
     */
    public static Task strToTask(String taskInfo) {
        String[] parsedInfo = taskInfo.split(" \\| ");
        String type = parsedInfo[0];
        boolean done = Integer.parseInt(parsedInfo[1]) == 1;
        String name = parsedInfo[2];
        Task task;
        String time;
        switch (type) {
        case "T":
            task = Todo.stringToTask(name);
            break;
        case "D":
            time = parsedInfo[3];
            task = Deadline.stringToTask(name, time);
            break;
        default:
            time = parsedInfo[3];
            task = Event.stringToTask(name, time);
            break;
        }
        if (done) {
            task.setDone();
        }
        return task;
    }

    /**
     * Marks <code>this</code> as done.
     */
    public void setDone() {
        isDone = true;
        assert isDone : "Task's isDone should be true";
    }

    /**
     * Returns a boolean indicating whether <code>this</code> is done.
     *
     * @return <code>true</code> if <code>this</code> is done; otherwise, it returns <code>false</code>
     */
    public boolean isDone() {
        return isDone;
    }
}

