import java.util.Optional;

public class ExitCommand implements Command {
    public void execute(TaskList taskList, Optional<Storage> storage) {
        Ui.sayBye();
    }
}
