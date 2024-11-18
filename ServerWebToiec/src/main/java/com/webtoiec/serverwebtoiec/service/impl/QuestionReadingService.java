package com.webtoiec.serverwebtoiec.service.impl;

import com.webtoiec.serverwebtoiec.entities.QuestionReading;
import com.webtoiec.serverwebtoiec.repository.QuestionReadingRepo;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionReadingService {

  @Autowired
  private QuestionReadingRepo questionReadingRepo;

  public Page<QuestionReading> findListQuestionReading(int page, int size, int readingId) {
    return questionReadingRepo.findByquestionreadingid(readingId, PageRequest.of(page - 1, size));
  }

  @Transactional
  public QuestionReading saveQuestionReading(QuestionReading questionReading, HttpServletRequest request) throws IOException {
    return questionReadingRepo.save(questionReading);
  }

  public void deleteQuestionReading(int id) {
    questionReadingRepo.deleteById(id);
  }
}