public class Event extends Task {
    public Event(String taskName) {
        super(taskName);
    }

    public String toString() {
        String[] parsedName = taskName.split(" /");
        String name = parsedName[0];
        String[] info = parsedName[1].split(" ");
        String time = "";

        for (int i = 1; i < info.length; i++) {
            time += (" " + info[i]);
        }
        time = time.trim();

        if (done) {
            return String.format("[E][\u2714] %s (at: %s)", name, time);
        } else {
            return String.format("[E][\u2718] %s (at: %s)", name, time);
        }
    }
}