package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GameDTO {
    private int id;
    private int quizId;
    private String code;

    public GameDTO(int quizId, String code) {
        this.quizId = quizId;
        this.code = code;
    }
}

