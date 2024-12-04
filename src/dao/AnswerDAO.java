package dao;

import model.Answer;

import java.util.List;

public interface AnswerDAO {
    void addAnswer(Answer answer, String table);
    List<Answer> getAnswers(int question_id, String table);
    String getAnswerById(int id, String table);
}
