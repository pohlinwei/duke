package duke.util;

import duke.task.Task;

/**
 * This class gives us a user interface.
 */
public class Ui {

    // for formatting purposes
    private static String prependSpace = "     ";

    // default statements
    private static String hi = "Hello! I'm Duke\nWhat can I do for you?";
    private static String bye = "Bye. Hope to see you again soon!";
    private static String listIntro = "Here are the tasks in your list:";
    private static String searchResultsIntro = "Here are the matching tasks in your list:";

    private static String sadFace = "\u2639";

    /**
     * Says hi to user.
     */
    public static String sayHi() {
        return hi;
    }

    /**
     * Says bye to user.
     */
    public static String sayBye() {
        return bye;
    }

    /**
     * Shows user a list of all tasks.
     */
    public static String showList(String tasksAsString) {
       return listIntro + "\n" + tasksAsString;
    }

    /**
     * Informs user that task is completed.
     *
     * @param completedTask task which has been successfully marked as done
     */
    public static String informDone(Task completedTask) {
        return String.format("Nice! I've marked this task as done:\n%s\n",
                completedTask);
    }

    /**
     * Informs user that the task has been deleted.
     *
     * @param deletedTask task which has been successfully deleted
     * @param tasksNum number of tasks remaining in the list
     */
    public static String informDeleted(Task deletedTask, int tasksNum) {
        // update user
        String response = String.format("Noted. I've removed this task:\n%s\n\nNow you have %d tasks in the list.",
                deletedTask, tasksNum);
        return response;
    }

    /**
     * Informs user that the task has been added.
     *
     * @param addedTask task which has been successfully added
     * @param tasksNum number of tasks in the list
     */
    public static String informAdded(Task addedTask, int tasksNum) {
        String response = String.format("Got it. I've added this task:\n%s\n\n"
                        + "Now you have %d tasks in the list.\n", addedTask, tasksNum);
       return response;
    }

    /**
     * Informs user that an error has occurred.
     *
     * @param e <code>Exception</code> encountered
     */
    public static String showError(Exception e) {
        return sadFace + " OOPS!!! " + e.getMessage();
    }

    public static String showSearchResults(String tasksAsString) {
        return searchResultsIntro + "\n" + tasksAsString;
    }
}