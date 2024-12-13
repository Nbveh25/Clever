package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuizDTO {
    private int id;
    private String quizName;
    private String quizDescription;
    private String quizType;
    private String quizIconPath;

    public QuizDTO(String quizName, String quizDescription, String quizType, String quizIconPath) {
        this.quizName = quizName;
        this.quizDescription = quizDescription;
        this.quizType = quizType;
        this.quizIconPath = quizIconPath;
    }
}

