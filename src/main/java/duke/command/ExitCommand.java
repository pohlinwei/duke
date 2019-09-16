package duke.command;

import duke.Manager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class returns a command that terminates the program.
 */
public class ExitCommand implements Command {
    private final static CommandType commandType = CommandType.BYE;

    /**
     * Bids farewell.
     *
     * @param manager manager which stores all inputs
     * @param storage storage which stores all inputs on the local hard disk, if any
     */
    public String execute(Manager manager, OptionalStorage storage) {
        return Ui.sayBye();
    }

    /**
     * Gets command type of <code>this</code>.
     *
     * @return command type of <code>this</code> command
     */
    public CommandType getCommandType() {
        return commandType;
    }
}
