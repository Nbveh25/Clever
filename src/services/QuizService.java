package services;

import dto.QuizDTO;

import java.util.List;

public interface QuizService {
    int addQuiz(QuizDTO quizDTO);
    List<QuizDTO> getQuizzes();
}