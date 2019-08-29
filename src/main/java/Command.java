import java.util.Optional;

/**
 * This allows commands, as indicated by a user's input, to be executed.
 */
public interface Command {
    /**
     * Executes the intended command.
     *
     * @param taskList task list which <code>this</code> task should be added to
     * @param storage storage which stores all tasks on the local hard disk, if any
     * @throws Exception if command cannot be executed
     */
    public void execute(TaskList taskList, Optional<Storage> storage) throws Exception;
}
