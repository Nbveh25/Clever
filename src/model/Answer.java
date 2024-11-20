package model;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
