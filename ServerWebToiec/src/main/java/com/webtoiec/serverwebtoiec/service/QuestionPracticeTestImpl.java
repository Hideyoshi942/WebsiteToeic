package com.webtoiec.serverwebtoiec.service;

import com.webtoiec.serverwebtoiec.entities.PracticeTest;
import com.webtoiec.serverwebtoiec.entities.QuestionPracticeTest;
import com.webtoiec.serverwebtoiec.repository.QuestionPracticeTestRepo;
import com.webtoiec.serverwebtoiec.service.impl.QuestionPracticeTestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionPracticeTestImpl implements QuestionPracticeTestService {
  @Autowired
  QuestionPracticeTestRepo questionpracticetestRepo;


  @Override
  public void save(QuestionPracticeTest questionpracticetest) {
    questionpracticetestRepo.save(questionpracticetest);
  }

  @Override
  public List<QuestionPracticeTest> getListQuestion(PracticeTest practicetest) {
    return questionpracticetestRepo.findByPracticetest(practicetest);
  }

  @Override
  public Page<QuestionPracticeTest> findListQuestion(int page, int size, PracticeTest practicetest) {
    Pageable pageable = PageRequest.of(page - 1, size);
    return questionpracticetestRepo.findByPracticetest(pageable, practicetest);
  }

}
