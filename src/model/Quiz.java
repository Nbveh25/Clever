package model;

import java.util.Objects;

public class Quiz {
    private String quizName;
    private String quizDescription;
    private String quizType;

    public Quiz(String quizName, String quizDescription, String quizType) {
        this.quizName = quizName;
        this.quizDescription = quizDescription;
        this.quizType = quizType;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(quizName, quiz.quizName) && Objects.equals(quizDescription, quiz.quizDescription) && Objects.equals(quizType, quiz.quizType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizName, quizDescription, quizType);
    }
}
