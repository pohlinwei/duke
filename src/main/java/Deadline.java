public class Deadline extends Task {
    private String time = "";
    private String name = "";

    public Deadline(String taskName) {
        super(taskName);
        String[] parsedName = taskName.split(" /");
        this.name = parsedName[0];
        String[] info = parsedName[1].split(" ");

        for (int i = 1; i < info.length; i++) {
            this.time += (" " + info[i]);
        }
        this.time = this.time.trim();
    }

    public String getInfo() {
        return String.format("D | %d | %s | %s", done ? 1 : 0, name, time);
    }

    @Override
    public String toString() {
        if (done) {
            return String.format("[D][\u2714] %s (by: %s)", name, time);
        } else {
            return String.format("[D][\u2718] %s (by: %s)", name, time);
        }
    }
}