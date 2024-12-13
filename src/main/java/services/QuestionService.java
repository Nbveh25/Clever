package services;


import dto.QuestionDTO;
import model.Question;

import java.util.List;

public interface QuestionService {
    int addQuestion(QuestionDTO questionDTO);
    List<QuestionDTO> getQuestions(int quizId);
    QuestionDTO toQuestionDTO(Question question);
}
