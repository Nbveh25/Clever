package services.impl;

import dao.AnswerDAO;
import dto.AnswerDTO;
import model.Answer;
import services.AnswerService;

import java.util.List;
import java.util.stream.Collectors;

public class AnswerServiceImpl implements AnswerService {
    private final AnswerDAO answerDAO;

    public AnswerServiceImpl() {
        this.answerDAO = new AnswerDAO();
    }

    @Override
    public void addAnswer(Answer answer, String table) {
        answerDAO.addAnswer(answer, table);
    }

    @Override
    public List<AnswerDTO> getAnswers(int question_id, String table) {
        return answerDAO.getAnswers(question_id, table).stream().map(this::toAnswerDTO).collect(Collectors.toList());
    }

    @Override
    public String getAnswerById(int id, String table) {
        return answerDAO.getAnswerById(id, table);
    }

    @Override
    public AnswerDTO toAnswerDTO(Answer answer) {
        return new AnswerDTO(
                answer.getId(),
                answer.getQuestion_id(),
                answer.getAnswer()
        );
    }
}
