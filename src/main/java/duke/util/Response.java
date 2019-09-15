package duke.util;

import duke.command.CommandType;

public class Response {
    private String message;
    private CommandType commandType;

    public Response(String message, CommandType commandType) {
        this.message = message;
        this.commandType = commandType;
    }

    public Response() {
        new Response("", CommandType.INVALID);
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String getMessage() {
        return message;
    }

    public boolean isExit() {
        return commandType.equals(CommandType.BYE);
    }
}
