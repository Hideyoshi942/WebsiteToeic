package com.webtoiec.serverwebtoiec.service.impl;

import com.webtoiec.serverwebtoiec.entities.PracticeTest;
import com.webtoiec.serverwebtoiec.entities.QuestionPracticeTest;
import java.util.List;
import org.springframework.data.domain.Page;

public interface QuestionPracticeTestService {
  void save(QuestionPracticeTest questionpracticetest);

  List<QuestionPracticeTest> getListQuestion(PracticeTest practicetest);

  Page<QuestionPracticeTest> findListQuestion(int page, int size, PracticeTest practicetest);
}
