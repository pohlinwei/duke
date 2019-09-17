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

public class TriviaManager implements Manager {
    private HashMap<String, String> quickQnsAndAns = new HashMap<>();
    private List<Trivia> trivias = new ArrayList<>();
    private Optional<Trivia> asked = Optional.empty();
    private Optional<Trivia> lastEdited = Optional.empty();

    public TriviaManager() {}

    public void addPrevious(Stream<String> storedTrivias) {
        storedTrivias.forEach(t -> {
            Trivia trivia = Trivia.strToTrivia(t);
            trivias.add(trivia);
            String question = trivia.getQuestion().toLowerCase();
            String answer = trivia.getAnswer();
            quickQnsAndAns.put(question, answer);
        });
    }

    public void removeAll() {
        quickQnsAndAns.clear();
        trivias.clear();
    }

    public void add(Trivia trivia) {
        String question = trivia.getQuestion();
        String answer = trivia.getAnswer();
        quickQnsAndAns.put(question.toLowerCase(), answer);
        trivias.add(trivia);
        lastEdited = Optional.of(trivia);
    }

    public String askRandom() {
        Random rand = new Random();
        int questionNum = rand.nextInt(trivias.size());
        Trivia trivia = trivias.get(questionNum);
        asked = Optional.of(trivia);
        return trivia.getQuestion();
    }

    public boolean checkAnswer(String response) {
        if (!asked.isPresent()) {
            assert false : "There should be a previously asked question";
        }
        Trivia trivia = asked.get();
        asked = Optional.empty();

        return trivia.isAnswerCorrect(response);
    }

    public String findAnswer(String question) {
        String qnsIgnoreCase = question.toLowerCase();
        return quickQnsAndAns.get(qnsIgnoreCase);
    }

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

    public int getSize() {
        return trivias.size();
    }

    public boolean isEmpty() {
        return trivias.isEmpty();
    }

    public Trivia getLastEdited() {
        if (lastEdited.isEmpty()) {
            assert false : "There should be a previously edited trivia";
        }

        return lastEdited.get();
    }

    public boolean hasAsked() {
        return asked.isPresent();
    }

    public String getAsked() {
        if (!asked.isPresent()) {
            assert false : "There should be a previously asked question";
        }
        return asked.get().getQuestion();
    }

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