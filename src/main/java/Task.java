abstract public class Task {
    protected String taskName;
    protected boolean done = false; // by default it's false

    public Task(String taskName) {
        this.taskName = taskName;
    }

    // returns a summary of the task's state and info
    abstract public String getInfo();

    public void done() {
        done = true;
    }

    public boolean isDone() {
        return done;
    }
}