package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AnswerDTO {
    private int id;
    private int question_id;
    private String answer;

    public AnswerDTO(int question_id, String answer) {
        this.question_id = question_id;
        this.answer = answer;
    }
}
