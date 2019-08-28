import java.util.Optional;

/**
 * This class allows a new task to be added to a <code>TaskList</code> object.
 */
public class AddCommand implements Command {
    Task task;

    /**
     * Returns a command which allows us to add a task to <code>taskList</code>.
     *
     * @param task task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes this command by adding <code>this</code> task to <code>taskList</code>.
     * Informs user that command has been executed.
     *
     * @param taskList task list which <code>this</code> task should be added to
     * @param storage storage which stores all tasks on the local hard disk, if any
     */
    public void execute(TaskList taskList, Optional<Storage> storage) {
        taskList.addTask(task);
        storage.ifPresent(s -> s.addTask(task));
        Ui.informAdded(taskList.getLastEditedTask(), taskList.getSize());
    }
}
