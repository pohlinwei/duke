package duke.task;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Optional;

import duke.Manager;
import duke.exception.NoSuchInputException;
import duke.exception.task.MultipleChecksException;

/**
 * This class returns a task manager which allows us to read, add, delete and update the status of tasks.
 */
public class TaskManager implements Manager {
    private List<Task> tasks = new ArrayList<>();
    private Optional<Task> lastEditedTask = Optional.empty();

    /**
     * Constructs a <code>TaskManager</code>.
     */
    public TaskManager() {

    }

    /**
     * Adds tasks that are retrieved from a <code>Stream</code> of strings representing a
     * summarised version of <code>task</code>s.
     *
     * <p>The <code>Stream</code> can be retrieved from a <code>Storage</code> object.
     *
     * @param previousTasks <code>Stream</code> of summarised string representation of <code>task</code>s
     */
    public void addPrevious(Stream<String> previousTasks) {
        tasks.addAll(toTaskList(previousTasks));
    }

    public void removeAll() {
        tasks.clear();
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
     * Deletes task with <code>taskNum</code> in <code>this</code> task manager.
     *
     * @param taskNum index of task to be deleted
     */
    public void deleteTask(int taskNum) throws NoSuchInputException {
        try {
            lastEditedTask = Optional.of(tasks.get(taskNum));
            tasks.remove(taskNum);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchInputException("No such task found");
        }
    }

    /**
     * Marks task with <code>taskNum</code> as done.
     *
     * @param taskNum index of task to be marked as completed
     * @throws MultipleChecksException if the task with index <code>taskNum</code> has been marked as done previously
     */
    public void markTaskDone(int taskNum) throws MultipleChecksException {
        try {
            Task completedTask = tasks.get(taskNum);
            if (completedTask.isDone()) {
                throw new MultipleChecksException(taskNum + 1);
            } else {
                completedTask.setDone();
                lastEditedTask = Optional.of(completedTask);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No such task");
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
     * Returns the number of tasks in this instance of <code>TaskManager</code>.
     *
     * @return number of tasks in this instance of <code>TaskManager</code>
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a <code>Stream</code> which represents every task in this instance of <code>TaskManager</code>.
     *
     * @return <code>Stream</code> which represents every task in this instance of <code>TaskManager</code>
     */
    public Stream<Task> getTasksAsStream() {
        return tasks.stream();
    }

    private List<Task> toTaskList(Stream<String> allTasks) {
        return allTasks.map(taskInfo -> toTask(taskInfo))
                .collect(Collectors.toList());
    }

    /**
     * Converts a string which represents a task to a <code>Task</code> instance.
     *
     * @param taskInfo string which summarises a task
     * @return task which is represented by the specified string
     */
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
