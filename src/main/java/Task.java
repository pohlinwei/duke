public class Task {
    protected String taskName;
    protected boolean done = false; // by default it's false

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void done() {
        done = true;
    }

    public String toString() {
        if (done) {
            return String.format("[\u2714] %s", taskName);
        } else {
            return String.format("[\u2718] %s", taskName);
        }
    }
}