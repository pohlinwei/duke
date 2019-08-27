import java.io.FileWriter;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.IntStream;

public class Storage {
    private String path = "";

    public Storage(String path) throws IOException {
        this.path = path;
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
    }

    public Stream<String> load() throws IOException {
        return Files.lines(Paths.get(path));
    }

    public void addTask(Task task) throws IOException {
        BufferedWriter w = new BufferedWriter(new FileWriter(path, true));
        w.write(task.getInfo() + System.lineSeparator());
        w.close();
    }

    public void update(List<Task> tasks) throws IOException {
        BufferedWriter w = new BufferedWriter(new FileWriter(path));
        tasks.stream()
                .forEach(task -> {
                    try {
                        w.write(task.getInfo() + System.lineSeparator());
                    } catch (IOException e) {
                        System.err.println("Unable to update storage: " + e.getMessage());
                    }
                });
        w.close();
    }
}
