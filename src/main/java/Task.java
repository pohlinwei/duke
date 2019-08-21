public class Task {
    protected String taskName;
    protected boolean done = false; // by default it's false

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void done() {
        done = true;
    }
}