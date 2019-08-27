public class Event extends Task {
    private String time = "";
    private String name = "";

    public Event(String taskName) {
        super(taskName);
        String[] parsedName = taskName.split(" /");
        this.name = parsedName[0];
        String[] info = parsedName[1].split(" ");

        for (int i = 1; i < info.length; i++) {
            time += (" " + info[i]);
        }
        time = time.trim();
    }

    public String getInfo() {
        return String.format("E | %d | %s | %s", done ? 1 : 0, name, time);
    }

    @Override
    public String toString() {
        if (done) {
            return String.format("[E][\u2714] %s (at: %s)", name, time);
        } else {
            return String.format("[E][\u2718] %s (at: %s)", name, time);
        }
    }
}