
class EmptyDescriptionException extends Exception {
    private String type;

    public EmptyDescriptionException(String task) {
        super();
        if (task.equals("event")) {
            type = "an event";
        } else if (task.equals("todo")) {
            type = "a todo";
        } else {
            // assumes that there are only 3 possibilities
            type = "a deadline";
        }
    }

    @Override
    public String getMessage() {
        return String.format("The description of %s cannot be empty.", type);
    }
}