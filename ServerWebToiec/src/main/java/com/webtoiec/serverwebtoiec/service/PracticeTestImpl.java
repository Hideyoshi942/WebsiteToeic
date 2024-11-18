package com.webtoiec.serverwebtoiec.service;

import com.webtoiec.serverwebtoiec.entities.PracticeTest;
import com.webtoiec.serverwebtoiec.repository.PracticeTestRepo;
import com.webtoiec.serverwebtoiec.service.impl.PracticeTestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PracticeTestImpl implements PracticeTestService {

  @Autowired
  PracticeTestRepo practiceTestRepo;

  @Override
  public void save(PracticeTest practicetest) {
    practiceTestRepo.save(practicetest);
  }

  @Override
  public List<PracticeTest> getPracticeTest(int practicetestid) {
    return practiceTestRepo.findBypracticetestid(practicetestid);
  }

  @Override
  public Page<PracticeTest> getPracticeTest(int page, int size) {
    return practiceTestRepo.findAll(PageRequest.of(page, size));
  }

  @Override
  public List<PracticeTest> getAllPracticeTest() {
    return practiceTestRepo.findAll();
  }

  @Override
  public void delete(int practicetestid) {
    practiceTestRepo.deleteById(practicetestid);
  }
}
