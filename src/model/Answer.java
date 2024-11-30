package model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Answer {
    private int id;
    private int question_id;
    private String answer;

    public Answer(int question_id, String answer) {
        this.question_id = question_id;
        this.answer = answer;
    }

    public Answer(int id, int question_id, String answer) {
        this.id = id;
        this.question_id = question_id;
        this.answer = answer;
    }

}
