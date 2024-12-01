package services.impl;

import dao.QuizDAO;
import dto.QuizDTO;
import model.Quiz;
import services.QuizService;

import java.util.List;

public class QuizServiceImpl implements QuizService {
    private final QuizDAO quizDAO;

    public QuizServiceImpl() {
        this.quizDAO = new QuizDAO();
    }

    @Override
    public int addQuiz(QuizDTO quizDTO) {
        return quizDAO.addQuiz(
                new Quiz(
                        quizDTO.getQuizName(),
                        quizDTO.getQuizDescription(),
                        quizDTO.getQuizType(),
                        quizDTO.getQuizIconPath()
                )
        );
    }

    @Override
    public List<QuizDTO> getQuizzes() {
        return quizDAO.getAllQuiz();
    }
}
