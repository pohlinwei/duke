import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Optional;

/**
 * This class returns a task list which allows us to read, add, delete and update the status of tasks.
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();
    private Optional<Task> lastEditedTask = Optional.empty();

    private TaskList() {

    }

    /**
     * Returns an instance of <code>TaskList</code>.
     *
     * @return an instance of <code>TaskList</code> that contains no <code>task</code>s
     */
    public static TaskList instanceOf() {
        return new TaskList();
    }

    /**
     * Adds tasks that are retrieved from a <code>Stream</code> of strings representing a
     * summarised version of <code>task</code>s.
     * <p><
     * The <code>Stream</code> can be retrieved from a <code>Storage</code> object.
     *
     * @param previousTasks <code>Stream</code> of summarised string representation of <code>task</code>s
     */
    public void addPreviousTasks(Stream<String> previousTasks) {
        tasks.addAll(toTaskList(previousTasks));
    }

    /**
     * Adds <code>newTask</code> to <code>this</code>.
     *
     * @param newTask task to be added
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
        lastEditedTask = Optional.of(newTask);
    }

    /**
     * Deletes task with <code>taskNum</code> in <code>this</code> task list.
     *
     * @param taskNum index of task to be deleted
     */
    public void deleteTask(int taskNum) {
        lastEditedTask = Optional.of(tasks.get(taskNum));
        tasks.remove(taskNum);
    }

    /**
     * Marks task with <code>taskNum</code> as done.
     *
     * @param taskNum index of task to be marked as completed
     * @throws MultipleChecksException if the task with index <code>taskNum</code> has been marked as done previously
     */
    public void markTaskDone(int taskNum) throws MultipleChecksException {
        Task completedTask = tasks.get(taskNum);
        if (completedTask.isDone()) {
            throw new MultipleChecksException(taskNum + 1);
        } else {
            completedTask.done();
            lastEditedTask = Optional.of(completedTask);
        }
    }

    /**
     * Gets the last task which has been added, deleted or marked as done.
     *
     * @return last task which has been added, deleted or marked as done
     */
    public Task getLastEditedTask() {
        return lastEditedTask.orElseThrow();
    }

    /**
     * Returns the number of tasks in this instance of <code>TaskList</code>
     *
     * @return number of tasks in this instance of <code>TaskList</code>
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a <code>Stream</code> which represents every task in this instance of <code>TaskList</code>.
     *
     * @return <code>Stream</code> which represents every task in this instance of <code>TaskList</code>.
     */
    public Stream<Task> getTasksAsStream() {
        return tasks.stream();
    }

    private List<Task> toTaskList(Stream<String> allTasks) {
        return allTasks.map(taskInfo -> toTask(taskInfo))
                .collect(Collectors.toList());
    }

    private Task toTask(String taskInfo) {
        return Task.strToTask(taskInfo);
    }

    @Override
    public String toString() {
        String listedTask = IntStream.range(0, tasks.size())
                .mapToObj(i -> String.format("%d. %s\n", (i + 1), tasks.get(i)))
                .reduce("", (prev, curr) -> prev + curr);
        return listedTask;
    }
}
