package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class Player {

    private int user_id;
    private int game_id;
    private String login;
    private int total_score;

    public Player(int user_id, int game_id, String login, int total_score) {
        this.user_id = user_id;
        this.game_id = game_id;
        this.login = login;
        this.total_score = total_score;
    }

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
