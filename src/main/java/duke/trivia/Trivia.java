package duke.trivia;

import duke.Input;

public class Trivia extends Input {
    private String question;
    private String answer;

    public Trivia(String question, String answer) {
        this.question = question.trim();
        this.answer = answer.trim();
    }

    String getQuestion() {
        return question;
    }

    String getAnswer() {
        return answer;
    }

    public String getInfo() {
        return String.format("%s | %s", question, answer);
    }

    boolean isAnswerCorrect(String response) {
        return response.equalsIgnoreCase(answer);
    }

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