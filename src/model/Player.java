package model;

import java.util.Objects;

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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getTotal_score() {
        return total_score;
    }

    public void setTotal_score(int total_score) {
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
