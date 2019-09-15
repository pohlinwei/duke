package duke.command.task;

import duke.task.TaskManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class allows a task to be deleted from a <code>TaskList</code> object.
 */
public class DeleteCommand extends TaskCommand {
    int taskNum;

    /**
     * Returns a command which allows us to remove a task from <code>taskList</code>.
     *
     * @param taskNum index of <code>task</code> to be removed from <code>taskList</code>
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    
    /**
     * Executes this command by removing task with <code>taskNum</code> from <code>taskList</code>.
     * Informs user that command has been executed.
     *
     * @param taskManager task list which the task should be added to
     * @param storage storage which stores all tasks on the local hard disk, if any
     */
    public String execute(TaskManager taskManager, OptionalStorage storage) throws IndexOutOfBoundsException {
        taskManager.deleteTask(taskNum);
        storage.update(taskManager.getTasksAsStream());
        return Ui.informDeleted(taskManager.getLastEditedTask(), taskManager.getSize());
    }
}
