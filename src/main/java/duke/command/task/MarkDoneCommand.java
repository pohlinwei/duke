package duke.command.task;

import duke.exception.task.MultipleChecksException;

import duke.task.TaskManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class allows a task to be marked as completed.
 */
public class MarkDoneCommand extends TaskCommand {
    int taskNum;

    /**
     * Returns a command which allows the (taskNum)th task to be marked as done.
     *
     * @param taskNum (taskNum)th of task in <code>taskList</code> to be marked as completed
     */
    public MarkDoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes this command by marking <code>task</code> with <code>taskNum</code> as done.
     * Informs that command has been executed.
     *
     * @param taskManager task manager which contains <code>this</code> task
     * @param storage storage which stores all tasks on the local hard disk, if any
     * @return string indicating whether the task with <code>taskNum</code> has been marked
     */
    public String execute(TaskManager taskManager, OptionalStorage storage) throws MultipleChecksException,
            IndexOutOfBoundsException {
        taskManager.markTaskDone(taskNum);
        storage.update(taskManager.getTasksAsStream());
        return Ui.informDone(taskManager.getLastEditedTask());
    }
}