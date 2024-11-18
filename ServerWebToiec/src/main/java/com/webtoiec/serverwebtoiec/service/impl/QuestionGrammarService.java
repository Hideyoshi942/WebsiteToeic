package com.webtoiec.serverwebtoiec.service.impl;

import com.webtoiec.serverwebtoiec.entities.Grammar;
import com.webtoiec.serverwebtoiec.entities.QuestionGrammar;
import com.webtoiec.serverwebtoiec.repository.QuestionGrammarRepo;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionGrammarService {
  @Autowired
  private QuestionGrammarRepo questionGrammarRepo;

  public Page<QuestionGrammar> findListQuestionGrammarId(int page, int size, int grammarid) {
    return questionGrammarRepo.findByquestiongrammarid(grammarid, PageRequest.of(page - 1, size));
  }

  public List<QuestionGrammar> findListQuestionGrammarid(int grammarid) {
    return questionGrammarRepo.findByquestiongrammarid(grammarid);
  }

  public Optional<QuestionGrammar> findquestiongrammarid(int id) {
    return questionGrammarRepo.findById(id);
  }

  @Transactional
  public QuestionGrammar saveQuestionGrammar(QuestionGrammar questionGrammar, HttpServletRequest request) throws IOException {
    QuestionGrammar qg = questionGrammarRepo.save(questionGrammar);
    return questionGrammarRepo.save(qg);
  }

  public void deleteQuestionGrammar(int id) {
    questionGrammarRepo.deleteById(id);
  }

  public List<QuestionGrammar> findListQuestionByGammarId(Grammar grammar) {
    return questionGrammarRepo.findByGrammar(grammar);
  }
}
