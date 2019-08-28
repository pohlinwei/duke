import java.util.Optional;
import java.util.Scanner;

public class Duke {
    private static String entries = "../data/entries.txt";

    public static void main(String[] args) {
        // set up
        TaskList taskList = TaskList.instanceOf();
        Optional<Storage> desiredStorage = Optional.empty();
        try {
            desiredStorage = Optional.of(new Storage(entries));
        } catch (Exception e) {
            Ui.showError(e);
        }
        Optional<Storage> storage = desiredStorage;
        storage.ifPresent(s -> taskList.addPreviousTasks(s.load()));

        // greet user
        Ui.sayHi();

        // get first user input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Optional<Command> currentCommand = Parser.parse(input);
        boolean isExitCommand = currentCommand.filter(c -> c instanceof ExitCommand).isPresent();

        while (!isExitCommand) {
            currentCommand.ifPresent(c -> {
                try {
                    c.execute(taskList, storage);
                } catch (Exception e) {
                    Ui.showError(e);
                }
            });

            input = sc.nextLine();
            currentCommand = Parser.parse(input);
            isExitCommand = currentCommand.filter(c -> c instanceof ExitCommand).isPresent();
        }

        Ui.sayBye();
    }
}
