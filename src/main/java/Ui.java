import java.util.Arrays;

/**
 * This class gives us a user interface.
 */
public class Ui {
    // for formatting purposes
    private static String line = "    ____________________________________________________________\n";
    private static String prependSpace = "     ";

    // default statements
    private static String hi = "Hello! I'm Duke\nWhat can I do for you?";
    private static String bye = "Bye. Hope to see you again soon!";
    private static String listIntro = "Here are the tasks in your list:";

    /**
     * Says hi to user.
     */
    public static void sayHi() {
        System.out.print(formatOutput(hi));
    }

    /**
     * Says bye to user.
     */
    public static void sayBye() {
        System.out.print(formatOutput(bye));
    }

    /**
     * Shows user a list of all tasks.
     */
    public static void showList(String tasksAsString) {
        System.out.print(formatOutput(listIntro + "\n" + tasksAsString));
    }

    /**
     * Informs user that task is completed.
     *
     * @param completedTask task which has been successfully marked as done
     */
    public static void informDone(Task completedTask) {
        System.out.print(formatOutput(String.format("Nice! I've marked this task as done:\n  %s\n",
                completedTask)));
    }

    /**
     * Informs user that the task has been deleted.
     *
     * @param deletedTask task which has been successfully deleted
     * @param tasksNum number of tasks remaining in the list
     */
    public static void informDeleted(Task deletedTask, int tasksNum) {
        // update user
        String response = String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                deletedTask, tasksNum);
        System.out.print(formatOutput(response));
    }

    /**
     * Informs user that the task has been added.
     *
     * @param addedTask task which has been successfully added
     * @param tasksNum number of tasks in the list
     */
    public static void informAdded(Task addedTask, int tasksNum) {
        String response = String.format("Got it. I've added this task:\n  %s\n" +
                        "Now you have %d tasks in the list.\n", addedTask, tasksNum);
        System.out.print(formatOutput(response));
    }

    /**
     * Informs user that an error has occurred.
     *
     * @param e <code>Exception</code> encountered
     */
    public static void showError(Exception e) {
        System.err.print(formatOutput("\u2639  OOPS!!! " + e.getMessage()));
    }

    private static String formatOutput(String str) {
        String accum = line;
        String[] allLines = str.split("\n");
        accum += Arrays.stream(allLines)
                .reduce("", (prev, curr) -> prev + prependSpace + curr + "\n");
        accum += line;
        return accum;
    }
}