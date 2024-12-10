package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
public class Player {

    private int user_id;
    private int game_id;
    private String login;
    private int total_score;
    private String icon_url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return user_id == player.user_id && game_id == player.game_id && total_score == player.total_score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, game_id, total_score);
    }
}
