package dao;

import dto.QuizDTO;
import model.Quiz;

import java.util.List;

public interface QuizDAO {
    int addQuiz(Quiz quiz);
    List<QuizDTO> getAllQuiz();
}
