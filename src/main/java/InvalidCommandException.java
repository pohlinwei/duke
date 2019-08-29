/**
 * This indicates that an error was encountered when trying to parse user's command.
 */
class InvalidCommandException extends UnsupportedOperationException {
    public InvalidCommandException() {
        super();
    }

    @Override
    public String getMessage() {
        return String.format("I'm sorry, but I don't know what that means :-(");
    }
}