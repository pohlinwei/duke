class InvalidCommandException extends UnsupportedOperationException {
    public InvalidCommandException() {
        super();
    }

    @Override
    public String getMessage() {
        return String.format("\u2639  OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}