package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PlayerDTO {
    private int user_id;
    private int game_id;
    private String login;
    private Integer total_score;
}
