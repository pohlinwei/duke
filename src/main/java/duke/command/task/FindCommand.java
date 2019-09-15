package duke.command.task;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.TaskManager;

import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

public class FindCommand extends TaskCommand {
    String query;

    public FindCommand(String query) {
        this.query = query;
    }

    public String execute(TaskManager taskManager, OptionalStorage storage) {
        List<String> results = taskManager.getTasksAsStream()
                .map(t -> t.toString())
                .filter(t -> containsQuery(t, query))
                .collect(Collectors.toList());
        String resultString = IntStream.range(0, results.size())
                .mapToObj(i -> String.format("%d. %s\n", (i + 1), results.get(i)))
                .reduce("", (prev, curr) -> prev + curr);
        return Ui.showSearchResults(resultString);
    }

    public boolean containsQuery(String task, String query) {
        String ignoreCaseTask = task.toLowerCase();
        String ignoreCaseQuery = query.toLowerCase();
        return ignoreCaseTask.contains(ignoreCaseQuery);
    }
}
