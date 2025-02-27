package duke.util.storage;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.stream.IntStream;

import duke.Input;
import duke.exception.LoadException;

/**
 * Storage which contains an actual storage. This allows inputs to be stored locally.
 */
class Storage {
    private String path;

    /**
     * Constructs a storage that allows inputs to be stored at a specified path.
     *
     * @param path the specified path
     * @throws LoadException if the file cannot be read/written to
     */
    Storage(String path) throws LoadException {
        this.path = path;
        try {
            if (path.equals("")) {
                throw new LoadException();
            } else if (!Files.exists(Paths.get(path))) {
                createFile(path);
            }
            if (!Files.isReadable(Paths.get(path)) || !Files.isWritable(Paths.get(path))) {
                throw new LoadException();
            }
        } catch (IOException e) {
            throw new LoadException();
        }
    }

    /**
     * Creates a new file at <code>path</code>.
     *
     * @param path path where new file will be located relative to main app's working directory
     */
    private void createFile(String path) throws LoadException {
        try {
            String[] parsedPath = path.split("/");
            // determine dirs path
            String dirPath = IntStream.range(0, parsedPath.length - 1)
                    .mapToObj(i -> parsedPath[i])
                    .reduce("", (prev, curr) -> prev + curr + "/");
            Files.createDirectories(Paths.get(dirPath));
            Files.createFile(Paths.get(path));
        } catch (IOException e) {
            throw new LoadException();
        }
    }

    /**
     * Returns a <code>Stream</code> of strings. Each string represents a summarised version of an input.
     *
     * @return strings of summarised version of all inputs
     */
    Stream<String> load() {
        try {
            return Files.lines(Paths.get(path));
        } catch (IOException e) {
            return Stream.of("");
        }
    }

    /**
     * Adds input to file found at <code>this</code> path.
     *
     * @param input input to be added to file
     */
    void add(Input input) {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(path, true));
            w.write(input.getInfo() + System.lineSeparator());
            w.close();
        } catch (IOException e) {
            assert false : "File should be writeable";
        }
    }

    /**
     * Rewrites the entire file to save updates made to <code>Manager</code>.
     *
     * @param inputs summarised string representation of all inputs found in <code>manager</code>
     */
    void update(Stream<? extends Input> inputs) {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(path));
            inputs.forEach(input -> {
                try {
                    w.write(input.getInfo() + System.lineSeparator());
                } catch (IOException e) {
                    System.err.println("Unable to update storage: " + e.getMessage());
                }
            });
            w.close();
        } catch (IOException e) {
            assert false : "File should be readable and writeable";
        }
    }
}
