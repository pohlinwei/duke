package duke.parser;

import java.util.Optional;

import duke.command.Command;
import duke.command.trivia.AnswerCommand;
import duke.command.trivia.AskCommand;
import duke.command.trivia.CheckCommand;
import duke.command.trivia.NewCommand;
import duke.command.trivia.RemoveCommand;
import duke.command.trivia.ShowCommand;
import duke.command.trivia.TriviaCmdType;
import duke.exception.DukeParseException;
import duke.exception.ExtraArgException;
import duke.exception.trivia.EmptyQuestionException;
import duke.exception.trivia.RemoveParseException;
import duke.exception.trivia.TriviaParseException;

/**
 * Trivia parser parses input for trivia command.
 */
class TriviaParser {
    private static final String SEPARATOR_QNS_AND_ANS = " /ans ";

    /**
     * Parses the input for a trivia command.
     *
     * @param triviaCmdType type of trivia command
     * @param details details of command
     * @return command that allows the specified <code>triviaCmdType</code> to be executed
     * @throws DukeParseException if details of command cannot be parsed successfully
     */
    static Optional<Command> parse(TriviaCmdType triviaCmdType, String details) throws DukeParseException {
        switch (triviaCmdType) {
        case ASK:
            return Optional.of(parseAsk(details));
        case ANSWER:
            return Optional.of(parseAnswer(details));
        case NEW:
            return Optional.of(parseNew(details));
        case CHECK:
            return Optional.of(parseCheck(details));
        case SHOW:
            return Optional.of(parseShow(details));
        case REMOVE:
            return Optional.of(parseRemove(details));
        default:
            assert false : "All trivia commands should be valid type";
            return Optional.empty();
        }
    }

    /**
     * Parses an ask command.
     *
     * @param details details of ask command
     * @return ask command
     * @throws ExtraArgException if extra arguments are found in details
     */
    private static Command parseAsk(String details) throws ExtraArgException {
        if (!details.equals("")) {
            throw new ExtraArgException("Ask command has extra argument. "
                + "To get a random question, just type 'ask'");
        }

        return new AskCommand();
    }

    /**
     * Parses an answer command.
     *
     * @param details details of answer command
     * @return answer command
     */
    private static Command parseAnswer(String details) {
        return new AnswerCommand(details);
    }

    /**
     * Parses a new command.
     *
     * @param details details of new command
     * @return new command
     * @throws TriviaParseException if parsing of details is unsuccessful
     */
    private static Command parseNew(String details) throws TriviaParseException {
        boolean hasSeparator = details.contains(SEPARATOR_QNS_AND_ANS);
        if (!hasSeparator) {
            String errorMsg = String.format("Missing %s between question and answer", SEPARATOR_QNS_AND_ANS);
            throw new TriviaParseException(errorMsg);
        }

        String question = details.split(SEPARATOR_QNS_AND_ANS)[0];
        String answer = details.split(SEPARATOR_QNS_AND_ANS)[1];

        if (question.equals("") || answer.equals("")) {
            throw new TriviaParseException("Both question and answer cannot be empty");
        }

        return new NewCommand(question, answer);
    }

    /**
     * Parses a check command.
     *
     * @param details details of check command
     * @return check command
     * @throws EmptyQuestionException if there is no question in <code>triviaManager</code>
     */
    private static Command parseCheck(String details) throws EmptyQuestionException {
        if (details.equals("")) {
            throw new EmptyQuestionException();
        }
        return new CheckCommand(details);
    }

    /**
     * Parses a show command.
     *
     * @param details details of show command
     * @return show command
     * @throws ExtraArgException if extra arguments are found in details
     */
    private static Command parseShow(String details) throws ExtraArgException {
        if (!details.equals("")) {
            throw new ExtraArgException("Show command has extra argument. "
                    + "To get a random question, just type 'show'");
        }

        return new ShowCommand();
    }

    /**
     * Parses a remove command.
     *
     * @param details details of remove command
     * @return remove command
     * @throws RemoveParseException if format of details is incorrect
     */
    private static Command parseRemove(String details) throws RemoveParseException {
        try {
            int triviaId = Integer.parseInt(details);
            return new RemoveCommand(triviaId);
        } catch (NumberFormatException e) {
            throw new RemoveParseException();
        }
    }
}
