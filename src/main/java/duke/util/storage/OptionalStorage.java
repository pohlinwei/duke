package duke.util.storage;

import duke.Input;
import duke.Manager;
import duke.exception.LoadException;
import duke.exception.task.NoStorageChangeException;

import java.util.Optional;
import java.util.stream.Stream;

public class OptionalStorage {
    private Optional<Storage> storage = Optional.empty();

    public OptionalStorage(String path) throws LoadException {
        storage = Optional.of(new Storage(path));
    }

    public OptionalStorage() {}

    public void add(Input input) {
        storage.ifPresent(s -> s.add(input));
    }

    public void update(Stream<? extends Input> inputs) {
        storage.ifPresent(s -> s.update(inputs));
    }

    public void load(Manager manager) {
        storage.ifPresent(s -> manager.addPrevious(s.load()));
    }

    public void updateTaskList(Manager manager) {
        manager.removeAll();
        load(manager);
    }

    public void update(String path) throws NoStorageChangeException {
        try {
            storage = Optional.of(new Storage(path));
        } catch (LoadException e) {
            throw new NoStorageChangeException(path);
        }
    }
}
