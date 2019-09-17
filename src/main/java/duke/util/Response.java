package duke.util;

import duke.command.CommandType;

/**
 * Duke's response.
 */
public class Response {
    private String message;
    private CommandType commandType;

    /**
     * Constructor for <code>Response</code> that has a message, which will be shown to the user.
     * It also indicates the command type of the previous command.
     * @param message message to be shown to user
     * @param commandType command type of the previous command
     */
    public Response(String message, CommandType commandType) {
        this.message = message;
        this.commandType = commandType;
    }

    /**
     * Constructor for an empty message which also indicates that the command is invalid.
     */
    public Response() {
        new Response("", CommandType.INVALID);
    }

    /**
     * Gets the command type of the previous command.
     * @return command type of the previous command
     */
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * Gets duke's response to user's input.
     * @return duke's response to user's input
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns <code>true</code> if the previous command is an exit command.
     * @return <code>true</code> if the previous command is an exit command.
     */
    public boolean isExit() {
        return commandType.equals(CommandType.BYE);
    }
}
