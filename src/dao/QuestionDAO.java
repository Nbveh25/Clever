package dao;

import model.Question;

import java.util.List;

public interface QuestionDAO {
    int addQuestion(Question question);
    List<Question> getQuizQuestions(int quizId);
}
