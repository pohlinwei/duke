public class Deadline extends Task {
    public Deadline(String taskName) {
        super(taskName);
    }

    @Override
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
            return String.format("[D][\u2714] %s (by: %s)", name, time);
        } else {
            return String.format("[D][\u2718] %s (by: %s)", name, time);
        }
    }
}