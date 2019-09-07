package duke.command;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.TaskList;

import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

public class FindCommand implements Command {
    private final static boolean IS_EXIT = false;
    String query;

    public FindCommand(String query) {
        this.query = query;
    }

    public String execute(TaskList taskList, OptionalStorage storage) {
        List<String> results = taskList.getTasksAsStream()
                .map(t -> t.toString())
                .filter(t -> t.contains(query))
                .collect(Collectors.toList());
        String resultString = IntStream.range(0, results.size())
                .mapToObj(i -> String.format("%d. %s\n", (i + 1), results.get(i)))
                .reduce("", (prev, curr) -> prev + curr);
        return Ui.showSearchResults(resultString);
    }

    public boolean isExit() {
        return IS_EXIT;
    }
}
