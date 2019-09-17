package duke.command.task;

import duke.task.Task;
import duke.task.TaskManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class allows a new task to be added to a <code>TaskManager</code> object.
 */
public class AddCommand extends TaskCommand {
    Task task;

    /**
     * Constructs a command which allows a task to be added.
     *
     * @param task task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
        assert this.task != null : "Empty task should not be added";
    }

    /**
     * Adds <code>this</code> task to <code>taskManager</code> and stores <code>this</code> in <code>storage</code>.
     * Informs that command has been executed when completed.
     *
     * @param taskManager task manager which <code>this</code> task should be added to
     * @param storage storage which stores all tasks on the local hard disk, if any
     * @return string indicating that <code>task</code> has been added
     */
    public String execute(TaskManager taskManager, OptionalStorage storage) {
        taskManager.addTask(task);
        storage.add(task);
        return Ui.informAdded(taskManager.getLastEditedTask(), taskManager.getSize());
    }
}