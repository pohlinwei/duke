package duke.command.task;

import duke.exception.NoSuchInputException;
import duke.task.TaskManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class allows a task to be deleted from a <code>TaskManager</code> object.
 */
public class DeleteCommand extends TaskCommand {
    int taskNum;

    /**
     * Returns a command which allows us to delete a task from <code>taskManager</code>.
     *
     * @param taskNum index of <code>task</code> to be removed from <code>taskList</code>
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    
    /**
     * Removes task with <code>taskNum</code> from <code>taskManager</code>. Informs that command has been executed.
     *
     * @param taskManager task manager which the task should be added to
     * @param storage storage which stores all tasks on the local hard disk, if any
     * @return string indicating whether the task has been successfully deleted
     */
    public String execute(TaskManager taskManager, OptionalStorage storage) throws NoSuchInputException {
        taskManager.deleteTask(taskNum);
        storage.update(taskManager.getTasksAsStream());
        return Ui.informDeleted(taskManager.getLastEditedTask(), taskManager.getSize());
    }
}
