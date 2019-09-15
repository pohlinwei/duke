package duke;

import java.util.stream.Stream;

public interface Manager {
    void addPrevious(Stream<String> previousInput);

    void removeAll();
}
