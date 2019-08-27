import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.text.ParseException;

public class Duke {
    // for formatting purposes
    private static String line = "    ____________________________________________________________\n";
    private static String prependSpace = "     ";

    // default statements by duke
    private static String hi = "Hello! I'm Duke\nWhat can I do for you?";
    private static String farewell = "Bye. Hope to see you again soon!";
    private static String listIntro = "Here are the tasks in your list:";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print(formatOutput(hi));

        String input = "";
        List<Task> tasks = new ArrayList<>();
        // get first user input
        input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] parsedInput = input.split(" ");
            String command = parsedInput[0];

            // get task details, if any
            String taskName = "";
            for (int i = 1; i < parsedInput.length; i++) {
                taskName += (" " + parsedInput[i]);
            }
            taskName = taskName.trim();

            try {
                if (command.equals("list")) {
                    // print all tasks
                    String listedTask = IntStream.range(0, tasks.size())
                            .mapToObj(i -> String.format("%d. %s\n", (i + 1), tasks.get(i)))
                            .reduce("", (prev, curr) -> prev + curr);
                    System.out.print(formatOutput(listIntro + "\n" + listedTask));
                } else if (command.equals("done")) {
                    int taskNum = Integer.parseInt(parsedInput[1]) - 1;
                    Task currentTask = tasks.get(taskNum);
                    if (currentTask.isDone()) {
                        throw new MultipleChecksException(taskNum + 1);
                    }
                    currentTask.done();
                    // update user
                    System.out.print(formatOutput(String.format("Nice! I've marked this task as done:\n  %s\n",
                            tasks.get(taskNum))));
                } else if (command.equals("delete")) {
                    int taskNum = Integer.parseInt(parsedInput[1]) - 1;
                    Task deletedTask = tasks.get(taskNum);
                    tasks.remove(taskNum);
                    // update user
                    String response = String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                                                    deletedTask, tasks.size());
                    System.out.print(formatOutput(response));
                } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    // if new tasks needs to be added
                    if (taskName.equals("")) {
                        throw new EmptyDescriptionException(command);
                    }

                    switch(command) {
                        case "todo":
                            tasks.add(new Todo(taskName));
                            break;
                        case "deadline":
                            if (!taskName.contains(" /")) {
                                throw new InvalidTimeException();
                            }
                            tasks.add(new Deadline(taskName));
                            break;
                        case "event":
                            if (!taskName.contains(" /")) {
                                throw new InvalidTimeException();
                            }
                            tasks.add(new Event(taskName));
                            break;
                    }

                    String response = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n",
                            tasks.get(tasks.size() - 1), tasks.size());
                    System.out.print(formatOutput(response));
                } else {
                    throw new InvalidCommandException();
                }
            } catch(MultipleChecksException e) {
                System.err.print(formatOutput(e.getMessage()));
            } catch (InvalidCommandException e) {
                System.err.print(formatOutput(e.getMessage()));
            } catch (EmptyDescriptionException e) {
                System.err.print(formatOutput(e.getMessage()));
            } catch (InvalidTimeException e) {
                System.err.print(formatOutput(e.getMessage()));
            } catch (ParseException e) {
                System.err.print(formatOutput(e.getMessage()));
            } finally {
                // get next user input
                input = sc.nextLine();
            }
        }

        // bid farewell
        System.out.println(formatOutput(farewell));
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
