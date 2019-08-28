import java.io.FileWriter;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.stream.IntStream;

public class Storage {
    private String path = "";

    public Storage(String path) throws LoadException {
        this.path = path;
        try {
            if (!Files.exists(Paths.get(path))) {
                String[] parsedPath = path.split("/");
                // determine dirs path
                String dirPath = IntStream.range(0, parsedPath.length - 1)
                        .mapToObj(i -> parsedPath[i])
                        .reduce("", (prev, curr) -> prev + curr + "/");
                Files.createDirectories(Paths.get(dirPath));
                // create file
                Files.createFile(Paths.get(path));
            }
        } catch (IOException e) {
            throw new LoadException();
        }
    }

    public Stream<String> load() {
        try {
           return Files.lines(Paths.get(path));
        } catch (IOException e) {
            return Stream.of("");
        }
    }

    public void addTask(Task task) {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(path, true));
            w.write(task.getInfo() + System.lineSeparator());
            w.close();
        } catch (IOException e) {
            // ignore since we are sure that we can read and write to file
        }
    }

    public void update(Stream<Task> tasks) {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(path));
            tasks.forEach(task -> {
                try {
                    w.write(task.getInfo() + System.lineSeparator());
                } catch (IOException e) {
                    System.err.println("Unable to update storage: " + e.getMessage());
                }
            });
            w.close();
        } catch (IOException e) {
            // we are certain that we are able to read and write to file
        }
    }

    private class LoadException extends IOException {
        public LoadException() {
            super();
        }
        @Override
        public String getMessage() {
            return String.format("Unable to read/write tasks to hard disk.\nEntries will not be saved.");
        }
    }
}
