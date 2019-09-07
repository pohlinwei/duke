package duke.util.storage;

import duke.exception.LoadException;
import duke.exception.NoStorageChangeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Optional;
import java.util.stream.Stream;

public class OptionalStorage {
    private Optional<Storage> storage = Optional.empty();

    public OptionalStorage(String path) throws LoadException {
        storage = Optional.of(new Storage(path));
    }

    public OptionalStorage() {}

    public void addTask(Task task) {
        storage.ifPresent(s -> s.addTask(task));
    }

    public void update(Stream<Task> tasks) {
        storage.ifPresent(s -> s.update(tasks));
    }

    public void load(TaskList taskList) {
        storage.ifPresent(s -> taskList.addPreviousTasks(s.load()));
    }

    public void updateTaskList(TaskList taskList) {
        taskList.removeAll();
        load(taskList);
    }

    public void update(String path) throws NoStorageChangeException {
        try {
            storage = Optional.of(new Storage(path));
        } catch (LoadException e) {
            throw new NoStorageChangeException(path);
        }
    }
}
