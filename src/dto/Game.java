package dto;

import java.util.Objects;

public class Game {

    private int id;
    private int quizId;
    private String code;

    public Game(int id, int quizId, String code) {
        this.id = id;
        this.quizId = quizId;
        this.code = code;
    }

    public Game(int quizId, String code) {
        this.quizId = quizId;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
