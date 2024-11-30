package services.impl;

import dao.QuizDAO;
import dto.QuizDTO;
import services.QuizService;

import java.util.List;

public class QuizServiceImpl implements QuizService {
    private final QuizDAO quizDAO;

    public QuizServiceImpl() {
        this.quizDAO = new QuizDAO();
    }

    @Override
    public int addQuiz(QuizDTO quizDTO) {
        return quizDAO.addQuiz(quizDTO);
    }

    @Override
    public List<QuizDTO> getQuizzes() {
        return quizDAO.getAllQuiz();
    }
}
