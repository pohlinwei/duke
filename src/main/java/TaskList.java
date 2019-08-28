import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Optional;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();
    private Optional<Task> lastEditedTask = Optional.empty();

    private TaskList() {

    }

    public static TaskList instanceOf() {
        return new TaskList();
    }

    public void addPreviousTasks(Stream<String> previousTasks) {
        tasks.addAll(toTaskList(previousTasks));
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        lastEditedTask = Optional.of(newTask);
    }

    public void deleteTask(int taskNum) {
        lastEditedTask = Optional.of(tasks.get(taskNum));
        tasks.remove(taskNum);
    }

    public void markTaskDone(int taskNum) throws MultipleChecksException {
        Task completedTask = tasks.get(taskNum);
        if (completedTask.isDone()) {
            throw new MultipleChecksException(taskNum + 1);
        } else {
            completedTask.done();
            lastEditedTask = Optional.of(completedTask);
        }
    }

    public Task getLastEditedTask() {
        return lastEditedTask.orElseThrow();
    }

    public int getSize() {
        return tasks.size();
    }

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
