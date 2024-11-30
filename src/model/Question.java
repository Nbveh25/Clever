package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
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
