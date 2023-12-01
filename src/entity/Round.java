package entity;

import java.util.ArrayList;

public interface Round {
    String getQuestion();
    Song getSong();
    String getCorrectAnswer();
    String getUserAnswer();
    void setUserAnswer(String userAnswer);
    boolean isUserAnswerCorrect();
    boolean isFinished();
    ArrayList<String> getMultipleChoiceAnswers();
}
