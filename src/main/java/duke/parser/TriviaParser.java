package duke.parser;

import java.util.Optional;

import duke.command.Command;
import duke.command.trivia.*;
import duke.exception.*;
import duke.exception.trivia.EmptyQuestionException;
import duke.exception.trivia.RemoveParseException;
import duke.exception.trivia.TriviaParseException;

class TriviaParser {
    private static final String SEPARATOR_QNS_AND_ANS = " /ans ";

    static Optional<Command> parse(TriviaCmdType triviaCmdType, String details) throws DukeParseException {
        switch(triviaCmdType) {
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

    private static Command parseAsk(String details) throws ExtraArgException {
        if (!details.equals("")) {
            throw new ExtraArgException("Ask command has extra argument. "
                + "To get a random question, just type 'ask'");
        }

        return new AskCommand();
    }

    private static Command parseAnswer(String details) {
        return new AnswerCommand(details);
    }

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

    private static Command parseCheck(String details) throws EmptyQuestionException {
        if (details.equals("")) {
            throw new EmptyQuestionException();
        }
        return new CheckCommand(details);
    }

    private static Command parseShow(String details) throws ExtraArgException {
        if (!details.equals("")) {
            throw new ExtraArgException("Show command has extra argument. "
                    + "To get a random question, just type 'show'");
        }

        return new ShowCommand();
    }

    private static Command parseRemove(String details) throws RemoveParseException {
        try {
            int triviaId = Integer.parseInt(details);
            return new RemoveCommand(triviaId);
        } catch (NumberFormatException e) {
            throw new RemoveParseException();
        }
    }
}
