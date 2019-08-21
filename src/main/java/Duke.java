import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

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

            if (command.equals("list")) {
                // print all tasks
                String listedTask = IntStream.range(0, tasks.size())
                        .mapToObj(i -> String.format("%d. %s\n", (i + 1), tasks.get(i)))
                        .reduce("", (prev, curr) -> prev + curr);
                System.out.print(formatOutput(listIntro + "\n" + listedTask));
            } else if (command.equals("done")) {
                int taskNum = Integer.parseInt(parsedInput[1]) - 1;
                // mark task as complete
                Task currentTask = tasks.get(taskNum);
                currentTask.done();
                // update user
                System.out.print(formatOutput(String.format("Nice! I've marked this task as done:\n  %s\n",
                        tasks.get(taskNum))));
            } else {
                System.out.print(formatOutput("added: " + input));
                // add new task
                tasks.add(new Task(input));
            }
            // get new input
            input = sc.nextLine();
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