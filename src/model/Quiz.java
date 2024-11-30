package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
public class Quiz {
    private int id;
    private String quizName;
    private String quizDescription;
    private String quizType;
    private String quizIconPath;

    public Quiz(String quizName, String quizDescription, String quizType, String quizIconPath) {
        this.quizName = quizName;
        this.quizDescription = quizDescription;
        this.quizType = quizType;
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
