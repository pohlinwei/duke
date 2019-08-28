import java.util.Optional;

public interface Command {
    public void execute(TaskList taskList, Optional<Storage> storage) throws Exception;
}
