package duke.trivia;

import duke.Manager;
import duke.exception.NoSuchInputException;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;
import java.util.stream.IntStream;

/**
 * Manager for all questions and answers.
 */
public class TriviaManager implements Manager {
    private HashMap<String, String> quickQnsAndAns = new HashMap<>();
    private List<Trivia> trivias = new ArrayList<>();
    private Optional<Trivia> asked = Optional.empty();
    private Optional<Trivia> lastEdited = Optional.empty();

    /**
     * Adds all previous trivias, as found in the stream of provided strings.
     *
     * @param storedTrivias stream of strings that represent trivias
     */
    public void addPrevious(Stream<String> storedTrivias) {
        storedTrivias.forEach(t -> {
            Trivia trivia = Trivia.strToTrivia(t);
            trivias.add(trivia);
            String question = trivia.getQuestion().toLowerCase();
            String answer = trivia.getAnswer();
            quickQnsAndAns.put(question, answer);
        });
    }

    /**
     * Removes all trivias.
     */
    public void removeAll() {
        quickQnsAndAns.clear();
        trivias.clear();
    }

    /**
     * Adds a new trivia to <code>this</code>.
     *
     * @param trivia trivia to be added
     */
    public void add(Trivia trivia) {
        String question = trivia.getQuestion();
        String answer = trivia.getAnswer();
        quickQnsAndAns.put(question.toLowerCase(), answer);
        trivias.add(trivia);
        lastEdited = Optional.of(trivia);
    }

    /**
     * Asks a question that is randomly selected.
     *
     * @return question that is randomly selected
     */
    public String askRandom() {
        Random rand = new Random();
        int questionNum = rand.nextInt(trivias.size());
        Trivia trivia = trivias.get(questionNum);
        asked = Optional.of(trivia);
        return trivia.getQuestion();
    }

    /**
     * Returns <code>true</code> if the specified response is equal to the correct answer. The check is case
     * insensitive.
     *
     * @param response the specified response
     * @return <code>true</code> if the specified response is equal to the correct answer
     */
    public boolean checkAnswer(String response) {
        if (!asked.isPresent()) {
            assert false : "There should be a previously asked question";
        }
        Trivia trivia = asked.get();
        asked = Optional.empty();

        return trivia.isAnswerCorrect(response);
    }

    /**
     * Finds the answer for the specified question.
     *
     * @param question question whose answer is to be returned
     * @return answer to the specified question
     */
    public String findAnswer(String question) {
        String qnsIgnoreCase = question.toLowerCase();
        return quickQnsAndAns.get(qnsIgnoreCase);
    }

    /**
     * Removes the (triviaId)th trivia from <code>this</code>.
     *
     * @param triviaId the (triviaId)th trivia to be removed
     * @throws NoSuchInputException if the (triviaId)th does not exist
     */
    public void remove(int triviaId) throws NoSuchInputException {
        int actualId = triviaId - 1;

        if (actualId >= trivias.size()) {
            throw new NoSuchInputException("No such question/answer.");
        }

        String question = trivias.get(actualId).getQuestion();
        quickQnsAndAns.remove(question);
        Trivia deletedTrivia = trivias.get(actualId);
        trivias.remove(actualId);
        lastEdited = Optional.of(deletedTrivia);
    }

    /**
     * Gets the number of trivias.
     *
     * @return number of trivias
     */
    public int getSize() {
        return trivias.size();
    }

    /**
     * Returns <code>true</code> if there is no trivia in <code>this</code>.
     *
     * @return <code>true</code> if there is no trivia in <code>this</code>.
     */
    public boolean isEmpty() {
        return trivias.isEmpty();
    }

    /**
     * Gets the last edited trivia.
     *
     * @return the last edited trivia
     */
    public Trivia getLastEdited() {
        if (lastEdited.isEmpty()) {
            assert false : "There should be a previously edited trivia";
        }

        return lastEdited.get();
    }

    /**
     * Returns <code>true</code> if there is a pending question that has not been answered.
     *
     * @return <code>true</code> if there is a pending question that has not been answered
     */
    public boolean hasAsked() {
        return asked.isPresent();
    }

    /**
     * Gets the pending question which has not been answered.
     *
     * @return the pending question which has not been answered.
     */
    public String getAsked() {
        if (!asked.isPresent()) {
            assert false : "There should be a previously asked question";
        }
        return asked.get().getQuestion();
    }

    /**
     * Returns all trivias as a stream.
     *
     * @return all trivias as a stream
     */
    public Stream<Trivia> getTriviasAsStream() {
        return trivias.stream();
    }

    @Override
    public String toString() {
        String allTrivias = IntStream.range(0, trivias.size())
            .mapToObj(i -> String.format("Trivia %d\n%s", (i + 1), trivias.get(i)))
            .reduce("", (prev, curr) -> prev + curr + "\n\n")
            .trim();
        return allTrivias;
    }
}