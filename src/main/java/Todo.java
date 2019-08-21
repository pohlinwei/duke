public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    public String toString() {
        if (done) {
            return String.format("[T][\u2714] %s", taskName);
        } else {
            return String.format("[T][\u2718] %s", taskName);
        }
    }
}