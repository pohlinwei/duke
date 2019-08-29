import java.util.Optional;

/**
 * This class allows a new task to be added to a <code>TaskList</code> object.
 */
public class ListCommand implements Command {
    /**
     * Executes this command by listing all tasks in <code>taskList</code>.
     *
     * @param taskList task list which contains all tasks
     * @param storage storage which stores all tasks on the local hard disk, if any
     */
    public void execute(TaskList taskList, Optional<Storage> storage) {
        Ui.showList(taskList.toString());
    }
}
