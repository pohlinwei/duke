import java.io.FileWriter;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.stream.IntStream;

/**
 * This class allows us to update and save any changes in <code>taskList</code> to a file.
 */
public class Storage {
    private String path = "";

    /**
     * Returns a storage which we can use to write and read the file stored at <code>path</code>.
     *
     * @param path path to file which the user would like to store information from <code>taskList</code>
     * @throws LoadException if the file cannot be read and or written to
     */
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

    /**
     * Returns a <code>Stream</code> of strings. Each string represents a summarised version of a <code>task</code>.
     *
     * @return strings of summarised version of all tasks
     */
    public Stream<String> load() {
        try {
           return Files.lines(Paths.get(path));
        } catch (IOException e) {
            return Stream.of("");
        }
    }

    /**
     * Adds <code>task</code> to file found at <code>this</code> path.
     *
     * @param task task to be added to file
     */
    public void addTask(Task task) {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(path, true));
            w.write(task.getInfo() + System.lineSeparator());
            w.close();
        } catch (IOException e) {
            // ignore since we are sure that we can read and write to file
        }
    }

    /**
     * Rewrites the entire file to save updates made to <code>taskList</code>.
     *
     * @param tasks summarised string representation of all tasks found in <code>taskList</code>
     */
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
