package duke.command;

import duke.Manager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class allows us to terminate the program.
 */
public class ExitCommand implements Command {
    private final static CommandType commandType = CommandType.BYE;

    /**
     * Bids farewell to user.
     *
     * @param manager task list which <code>this</code> task should be added to
     * @param storage storage which stores all tasks on the local hard disk, if any
     */
    public String execute(Manager manager, OptionalStorage storage) {
        return Ui.sayBye();
    }

    public CommandType getCommandType() {
        return commandType;
    }
}
