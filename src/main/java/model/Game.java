package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
public class Game {

    private int id;
    private int quizId;
    private String code;

    public Game(int quizId, String code) {
        this.quizId = quizId;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id && quizId == game.quizId && Objects.equals(code, game.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quizId, code);
    }
}
