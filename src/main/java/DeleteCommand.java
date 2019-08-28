import java.util.Optional;

public class DeleteCommand implements Command {
    int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    // todo handle exceptions
    public void execute(TaskList taskList, Optional<Storage> storage) {
        taskList.deleteTask(taskNum);
        storage.ifPresent(s -> s.update(taskList.getTasksAsStream()));
        Ui.informDeleted(taskList.getLastEditedTask(), taskList.getSize());
    }
}
