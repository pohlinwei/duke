package duke.util.storage;

import duke.Input;
import duke.Manager;
import duke.exception.LoadException;
import duke.exception.task.NoStorageChangeException;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Optional storage which may contain an actual storage. This allows inputs to be stored locally.
 */
public class OptionalStorage {
    private Optional<Storage> storage = Optional.empty();

    /**
     * Constructs an optional storage that allows inputs to be stored at a specified path.
     *
     * @param path the specified path
     * @throws LoadException if the file cannot be read/written to
     */
    public OptionalStorage(String path) throws LoadException {
        storage = Optional.of(new Storage(path));
    }

    /**
     * Constructs an optional storage that does not allow inputs to be stored locally.
     */
    public OptionalStorage() {}

    /**
     * Adds an input to the local file.
     * @param input input to be added
     */
    public void add(Input input) {
        storage.ifPresent(s -> s.add(input));
    }

    /**
     * Updates storage with the specified inputs.
     * @param inputs stream of specified inputs
     */
    public void update(Stream<? extends Input> inputs) {
        storage.ifPresent(s -> s.update(inputs));
    }

    /**
     * Loads all inputs of <code>this</code> onto a specified manager.
     *
     * @param manager the specified manager
     */
    public void load(Manager manager) {
        storage.ifPresent(s -> manager.addPrevious(s.load()));
    }

    /**
     * Updates the manager with the inputs found at the specified path.
     * @param manager manager to be updated
     */
    public void updateManager(Manager manager) {
        manager.removeAll();
        load(manager);
    }

    /**
     * Changes the local storage to the specified path.
     * @param path the specified path
     * @throws NoStorageChangeException if the change is unsuccessful
     */
    public void update(String path) throws NoStorageChangeException {
        try {
            storage = Optional.of(new Storage(path));
        } catch (LoadException e) {
            throw new NoStorageChangeException(path);
        }
    }
}
