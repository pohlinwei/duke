class InvalidTimeException extends UnsupportedOperationException {
    public InvalidTimeException() {
        super();
    }

    @Override
    public String getMessage() {
        return String.format("\u2639  OOPS!!! Please include a time.");
    }
}