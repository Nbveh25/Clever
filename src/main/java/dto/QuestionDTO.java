package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class QuestionDTO {
    private int id;
    private int quizId;
    private String question;
    private String typeQuestion;
    private String mediaPath;



    public QuestionDTO(int quizId, String question, String typeQuestion, String mediaPath) {
        this.quizId = quizId;
        this.question = question;
        this.typeQuestion = typeQuestion;
        this.mediaPath = mediaPath;
    }
}
