package model;

import java.util.Objects;

public class Quiz {
    private int id;
    private String quizName;
    private String quizDescription;
    private String quizType;
    private String quizIconPath;

    public Quiz(int id, String quizName, String quizDescription, String quizType, String quizIconPath) {
        this.id = id;
        this.quizName = quizName;
        this.quizDescription = quizDescription;
        this.quizType = quizType;
        this.quizIconPath = quizIconPath;
    }

    public Quiz(String quizName, String quizDescription, String quizType, String quizIconPath) {
        this.quizName = quizName;
        this.quizDescription = quizDescription;
        this.quizType = quizType;
        this.quizIconPath = quizIconPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public String getQuizIconPath() {
        return quizIconPath;
    }

    public void setQuizIconPath(String quizIconPath) {
        this.quizIconPath = quizIconPath;
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
