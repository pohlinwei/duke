import java.util.Optional;

/**
 * This class allows a task to be marked as completed.
 */
public class MarkDoneCommand implements Command {
    int taskNum;

    /**
     * Returns a command which allows us to add a task to <code>taskList</code>.
     *
     * @param taskNum id of <code>task</code> in <code>taskList</code> to be marked as completed
     */
    public MarkDoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes this command by marking <code>task</code> with <code>taskNum</code> as done.
     * Informs user that command has been executed.
     *
     * @param taskList task list which contains <code>this</code> task
     * @param storage storage which stores all tasks on the local hard disk, if any
     */
    public void execute(TaskList taskList, Optional<Storage> storage) throws MultipleChecksException {
        taskList.markTaskDone(taskNum);
        storage.ifPresent(s -> s.update(taskList.getTasksAsStream()));
        Ui.informDone(taskList.getLastEditedTask());
    }
}
