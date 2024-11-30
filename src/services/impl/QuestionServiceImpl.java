package services.impl;

import dao.QuestionDAO;
import dto.QuestionDTO;
import model.Question;
import services.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDAO questionDAO;

    public QuestionServiceImpl() {
        this.questionDAO = new QuestionDAO();
    }

    @Override
    public int addQuestion(QuestionDTO questionDTO) {
        return questionDAO.addQuestion(questionDTO);
    }

    @Override
    public List<QuestionDTO> getQuestions(int quizId) {
        return questionDAO.getQuizQuestions(quizId).stream().map(this::toQuestionDTO).collect(Collectors.toList());
    }

    @Override
    public QuestionDTO toQuestionDTO(Question question) {
        return new QuestionDTO(
                question.getId(),
                question.getQuizId(),
                question.getQuestion(),
                question.getTypeQuestion(),
                question.getMediaPath()
        );
    }


}
