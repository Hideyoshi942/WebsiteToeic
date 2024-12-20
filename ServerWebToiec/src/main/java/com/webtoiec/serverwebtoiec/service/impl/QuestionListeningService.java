package com.webtoiec.serverwebtoiec.service.impl;

import com.webtoiec.serverwebtoiec.entities.QuestionListening;
import com.webtoiec.serverwebtoiec.repository.QuestionListeningRepo;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionListeningService {
  @Autowired
  private QuestionListeningRepo questionListeningRepo;

  public Page<QuestionListening> findListQuestionListeningId(int page, int size, int listeningId) {
    return questionListeningRepo.findByquestionlisteningid(listeningId, PageRequest.of(page - 1, size));
  }

  public List<QuestionListening> findListQuestionListeningId(int listeningId) {
    return questionListeningRepo.findByquestionlisteningid(listeningId);
  }

  public Optional<QuestionListening> findQuestionListeningById(int id) {
    return questionListeningRepo.findById(id);
  }

  @Transactional
  public QuestionListening saveQuestionListening(QuestionListening questionListening, HttpServletRequest request) throws IOException {
    QuestionListening ql = questionListeningRepo.save(questionListening);
    if (ql.getQuestionlisteningimagedata() != null) {
      String rootDirectory = request.getSession().getServletContext().getRealPath("/");
      Path path = Paths.get(rootDirectory + "/resources/static/img/questionListeningId=" + ql.getQuestionlisteningid() + ".png");
      Files.write(path, ql.getQuestionlisteningimagedata());
    }
    return questionListeningRepo.save(ql);
  }

  public void deleteQuestionListening(int id) {
    questionListeningRepo.deleteById(id);
  }
}
