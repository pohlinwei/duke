package duke.command;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.TaskList;

import duke.util.Storage;
import duke.util.Ui;

public class FindCommand implements Command {
    String query;

    public FindCommand(String query) {
        this.query = query;
    }

    public String execute(TaskList taskList, Optional<Storage> storage) {
        List<String> results = taskList.getTasksAsStream()
                .map(t -> t.toString())
                .filter(t -> t.contains(query))
                .collect(Collectors.toList());
        String resultString = IntStream.range(0, results.size())
                .mapToObj(i -> String.format("%d. %s\n", (i + 1), results.get(i)))
                .reduce("", (prev, curr) -> prev + curr);
        return Ui.showSearchResults(resultString);
    }
}
