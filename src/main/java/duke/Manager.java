package duke;

import java.util.stream.Stream;

/**
 * Manager that manages valid inputs.
 */
public interface Manager {
    /**
     * Adds all previous inputs.
     *
     * @param previousInput stream of previous inputs that are represented as strings
     */
    void addPrevious(Stream<String> previousInput);

    /**
     * Removes all inputs from <code>this</code>.
     */
    void removeAll();
}
