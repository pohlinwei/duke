package duke.command;

import duke.task.TaskList;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class allows us to terminate the program.
 */
public class ExitCommand implements Command {
    private final static boolean IS_EXIT = true;

    /**
     * Bids farewell to user.
     *
     * @param taskList task list which <code>this</code> task should be added to
     * @param storage storage which stores all tasks on the local hard disk, if any
     */
    public String execute(TaskList taskList, OptionalStorage storage) {
        return Ui.sayBye();
    }

    public boolean isExit() {
        return IS_EXIT;
    }
}
