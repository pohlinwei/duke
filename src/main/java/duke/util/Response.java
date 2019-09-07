package duke.util;

public class Response {
    private String message;
    private boolean isExit;

    public Response(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }

    public Response() {
        new Response("", false);
    }

    public boolean isExit() {
        return isExit;
    }

    public String getMessage() {
        return message;
    }
}
