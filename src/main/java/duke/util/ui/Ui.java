package duke.util.ui;

import duke.task.Task;
import duke.trivia.Trivia;

/**
 * UI that formats duke's responses.
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
     *
     * @return greeting
     */
    public static String sayHi() {
        return hi;
    }

    /**
     * Says bye to user.
     *
     * @returns farewell
     */
    public static String sayBye() {
        return bye;
    }

    /**
     * Shows user a list of all tasks.
     *
     * @return list of tasks
     */
    public static String showList(String tasksAsString) {
        return listIntro + "\n" + tasksAsString;
    }

    /**
     * Informs user that task is completed.
     *
     * @param completedTask task which has been successfully marked as done
     * @return string indicating that specified task has been marked as completed
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
     * @return string indicating that the specified task has been deleted
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
     * @return string informing that the specified task has been successfully added
     */
    public static String informAdded(Task addedTask, int tasksNum) {
        String response = String.format("Got it. I've added this task:\n%s\n\n"
                        + "Now you have %d tasks in the list.\n", addedTask, tasksNum);
        return response;
    }

    /**
     * Informs user that the storage has been added.
     *
     * @param path path to file where inputs will be stored
     * @return string informing that the storage has been changed
     */
    public static String informChanged(String path) {
        String response = String.format("Done! Tasks from %s have been loaded and all new entries will be stored at %s",
            path, path);
        return response;
    }

    /**
     * Informs user that an error has occurred.
     *
     * @param e <code>Exception</code> encountered
     * @return error message
     */
    public static String showError(Exception e) {
        return sadFace + " OOPS!!! " + e.getMessage();
    }

    /**
     * Shows user the search results.
     *
     * @param tasksAsString search results
     * @return formatted search results
     */
    public static String showSearchResults(String tasksAsString) {
        return searchResultsIntro + "\n" + tasksAsString;
    }

    /**
     * Informs user that the answer has been found.
     *
     * @param answer answer to question
     * @return formatted answer
     */
    public static String informAnswer(String answer) {
        return String.format("%s\n%s", foundAnswer, answer);
    }

    /**
     * Informs user that the new trivia has been added.
     *
     * @param trivia new trivia that has been added
     * @return string informing
     */
    public static String informNew(Trivia trivia) {
        return String.format("%s\n%s", newInformation, trivia);
    }

    /**
     * Shows the user the randomly selected question.
     *
     * @param question the randomly selected question
     * @return formatted question
     */
    public static String showQuestion(String question) {
        return String.format("%s\n%s", askQuestion, question);
    }

    /**
     * Shows the user all questions and answers available in trivia manager.
     *
     * @param triviasAsList list of all questions and answers
     * @return formatted list of all question and answers
     */
    public static String showTrivia(String triviasAsList) {
        return String.format("%s\n\n%s", triviasListIntro, triviasAsList);
    }

    /**
     * Informs the user that the specified trivia has been removed from trivia manager.
     *
     * @param deletedTrivia the specified trivia to be removed
     * @param triviasNum the number of questions and answers left after deletion
     * @return formatted string which indicates that the specified trivia has been deleted
     */
    public static String informRemoved(Trivia deletedTrivia, int triviasNum) {
        String response = String.format("Noted. I've removed:\n%s\n\nNow you have %d questions in your trivia bank.",
                deletedTrivia, triviasNum);
        return response;
    }

    /**
     * Informs user whether the previous question is correctly answered.
     * @param isCorrect whether the previous question is correctly answered
     * @return formatted string which indicates whether the previous question is correctly answered
     */
    public static String informResult(boolean isCorrect) {
        if (isCorrect) {
            return String.format("Hooray! You got it right %s", smileyFace);
        } else {
            return String.format("Oops! %s Try again the next time round.", sadFace);
        }
    }
}