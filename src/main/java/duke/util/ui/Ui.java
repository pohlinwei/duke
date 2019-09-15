package duke.util.ui;

import duke.task.Task;
import duke.trivia.Trivia;

/**
 * This class gives us a user interface.
 */
public class Ui {
    // default statements
    private static String hi = "Hello! I'm Duke. What can I do for you?";
    private static String bye = "Bye. Hope to see you again soon!";

    // default statements for task commands
    private static String listIntro = "Here are the tasks in your list:";
    private static String searchResultsIntro = "Here are the matching tasks in your list:";

    // default statements for trivia commands
    private static String foundAnswer = "Here is the answer to your question:";
    private static String newInformation = "My brains just grew in size! I have added:";
    private static String askQuestion = "Here is a question for you:";
    private static String triviasListIntro = "Here is your question bank:";

    private static String sadFace = "\u2639";
    private static String smileyFace = "\u263A";

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

    public static String informChanged(String path) {
        String response = String.format("Done! Tasks from %s have been loaded and all new entries will be stored at %s",
            path, path);
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

    public static String informAnswer(String answer) {
        return String.format("%s\n%s", foundAnswer, answer);
    }

    public static String informNew(Trivia trivia) {
        return String.format("%s\n%s", newInformation, trivia);
    }

    public static String showQuestion(String question) {
        return String.format("%s\n%s", askQuestion, question);
    }

    public static String showTrivia(String triviasAsList) {
        return String.format("%s\n\n%s", triviasListIntro, triviasAsList);
    }

    public static String informRemoved(Trivia deletedTrivia, int triviasNum) {
        String response = String.format("Noted. I've removed:\n%s\n\nNow you have %d questions in your trivia bank.",
                deletedTrivia, triviasNum);
        return response;
    }

    public static String informResult(boolean isCorrect) {
        if (isCorrect) {
            return String.format("Hooray! You got it right %s", smileyFace);
        } else {
            return String.format("Oops! %s Try again the next time round.", sadFace);
        }
    }
}