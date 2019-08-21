import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Duke {
    // for formatting purposes
    private static String line = "    ____________________________________________________________\n";
    private static String prependSpace = "     ";

    // greetings
    private static String hi = "Hello! I'm Duke\nWhat can I do for you?";
    private static String farewell = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print(formatOutput(hi));

        String input = "";
        List<String> tasks = new ArrayList<>();
        // get first user input
        input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                // print all tasks
                String listedTask = IntStream.range(0, tasks.size())
                        .mapToObj(i -> String.format("%d. %s\n", (i + 1), tasks.get(i)))
                        .reduce("", (prev, curr) -> prev + curr);
                System.out.print(formatOutput(listedTask));

            } else {
                System.out.print(formatOutput("added: " + input));
                // add new task
                tasks.add(input);
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