package duke.command.task;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.TaskManager;

import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class allows tasks that contain a specified expression to be searched.
 */
public class FindCommand extends TaskCommand {
    String query;

    /**
     * Returns a command that allows the tasks with the expression <code>query</code> to be searched.
     *
     * @param query expression to be searched for
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Finds tasks that contain the expression <code>query</code>. This method is not case-sensitive.
     *
     * @param taskManager task manager that contains tasks
     * @param storage storage which stores all tasks on the local hard disk, if any
     * @return tasks that contain <code>query</code>
     */
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

    /**
     * Checks whether <code>task</code> contains <code>query</code>.
     *
     * @param task task to be checked
     * @param query string to be queried from <code>task</code>
     * @return whether <code>task</code> contains <code>query</code>
     */
    private boolean containsQuery(String task, String query) {
        String ignoreCaseTask = task.toLowerCase();
        String ignoreCaseQuery = query.toLowerCase();
        return ignoreCaseTask.contains(ignoreCaseQuery);
    }
}
