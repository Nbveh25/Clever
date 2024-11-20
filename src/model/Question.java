package model;

import java.util.Objects;

public class Question {
    private int id;
    private int quizId;
    private String question;
    private String typeQuestion;
    private String mediaPath;

    public Question(int quizId, String question, String typeQuestion, String mediaPath) {
        this.quizId = quizId;
        this.question = question;
        this.typeQuestion = typeQuestion;
        this.mediaPath = mediaPath;
    }

    public Question(int id, int quizId, String question, String typeQuestion, String mediaPath) {
        this.id = id;
        this.quizId = quizId;
        this.question = question;
        this.typeQuestion = typeQuestion;
        this.mediaPath = mediaPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(String typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return quizId == question1.quizId && Objects.equals(question, question1.question) && Objects.equals(typeQuestion, question1.typeQuestion) && Objects.equals(mediaPath, question1.mediaPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizId, question, typeQuestion, mediaPath);
    }
}
