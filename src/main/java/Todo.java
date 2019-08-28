public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    public String getInfo() {
        return String.format("T | %d | %s", done ? 1 : 0, taskName);
    }

    static Task stringToTask(String name) {
        return new Todo(name);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", done ? SYMBOL_COMPLETE : SYMBOL_INCOMPLETE, taskName);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            return this.taskName == (((Event) o).taskName);
        } else {
            return false;
        }
    }
}