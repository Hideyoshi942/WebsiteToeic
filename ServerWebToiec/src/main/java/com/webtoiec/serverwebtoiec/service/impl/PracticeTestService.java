package com.webtoiec.serverwebtoiec.service.impl;

import com.webtoiec.serverwebtoiec.entities.PracticeTest;
import java.util.List;
import org.springframework.data.domain.Page;

public interface PracticeTestService {
  void save(PracticeTest practicetest);

  List<PracticeTest> getPracticeTest(int practicetestid);

  Page<PracticeTest> getPracticeTest(int page, int limit);

  List<PracticeTest> getAllPracticeTest();

  void delete(int practicetestid);
}
