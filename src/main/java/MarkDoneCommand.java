import java.util.Optional;

public class MarkDoneCommand implements Command {
    int taskNum;

    public MarkDoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList taskList, Optional<Storage> storage) throws MultipleChecksException {
        taskList.markTaskDone(taskNum);
        storage.ifPresent(s -> s.update(taskList.getTasksAsStream()));
        Ui.informDone(taskList.getLastEditedTask());
    }
}
