import java.util.Scanner;
import java.util.Arrays;

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
        // get first user input
        input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.print(formatOutput(input));
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