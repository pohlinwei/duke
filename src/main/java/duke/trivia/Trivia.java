package duke.trivia;

import duke.Input;

/**
 * An <code>abstract</code> class representing a trivia with a <code>question</code> and an <code>answer</code>.
 */
public class Trivia extends Input {
    private String question;
    private String answer;

    /**
     * Constructs a trivia that has a question and an answer.
     *
     * @param question question that is associated with the answer
     * @param answer answer that is associated with the question
     */
    public Trivia(String question, String answer) {
        this.question = question.trim();
        this.answer = answer.trim();
    }

    /**
     * Gets question of <code>this</code>.
     *
     * @return <code>this</code> question
     */
    String getQuestion() {
        return question;
    }

    /**
     * Gets answer of <code>this</code>.
     *
     * @return <code>this</code> answer
     */
    String getAnswer() {
        return answer;
    }

    /**
     * Gets summarised version of <code>this</code>.
     *
     * @return string which summarises <code>this</code>
     */
    public String getInfo() {
        return String.format("%s | %s", question, answer);
    }

    /**
     * Returns <code>true</code> if the response is equal to <code>this</code> answer. The comparison is not
     * case sensitive.
     *
     * @param response response to <code>this</code> question
     * @return <code>true</code> if the response is equal to <code>this</code> question when capitalisation is ignored
     */
    boolean isAnswerCorrect(String response) {
        return response.equalsIgnoreCase(answer);
    }

    /**
     * Converts a string which summarises a trivia to a trivia.
     *
     * @param triviaStr string summarising the trivia
     * @return trivia that is represented by the specified string
     */
    static Trivia strToTrivia(String triviaStr) {
        String[] qnsAndAns = triviaStr.split("\\|");
        String qns = qnsAndAns[0];
        String ans = qnsAndAns[1];
        return new Trivia(qns, ans);
    }

    @Override
    public String toString() {
        return String.format("Q: %s\nA: %s", question, answer);
    }
}