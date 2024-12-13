package services.impl;

import dao.AnswerDAO;
import dao.impl.AnswerDAOImpl;
import dto.AnswerDTO;
import model.Answer;
import services.AnswerService;

import java.util.List;
import java.util.stream.Collectors;

public class AnswerServiceImpl implements AnswerService {
    private final AnswerDAO answerDAO;

    public AnswerServiceImpl() {
        this.answerDAO = new AnswerDAOImpl();
    }

    @Override
    public void addAnswer(AnswerDTO answerDTO, String table) {
        answerDAO.addAnswer(
                new Answer(
                        answerDTO.getId(),
                        answerDTO.getQuestion_id(),
                        answerDTO.getAnswer()
                ),
                table);
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
