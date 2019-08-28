import java.util.Optional;

public class ListCommand implements Command {
    public void execute(TaskList taskList, Optional<Storage> storage) {
        Ui.showList(taskList.toString());
    }
}
