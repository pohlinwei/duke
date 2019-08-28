import java.util.Optional;

public class AddCommand implements Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList taskList, Optional<Storage> storage) {
        taskList.addTask(task);
        storage.ifPresent(s -> s.addTask(task));
        Ui.informAdded(taskList.getLastEditedTask(), taskList.getSize());
    }
}
