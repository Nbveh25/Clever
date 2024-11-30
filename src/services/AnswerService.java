package services;

import dto.AnswerDTO;
import model.Answer;

import java.util.List;

public interface AnswerService {
    void addAnswer(Answer answer, String table);
    List<AnswerDTO> getAnswers(int question_id, String table);
    public String getAnswerById(int id, String table);
    AnswerDTO toAnswerDTO(Answer answer);
}
