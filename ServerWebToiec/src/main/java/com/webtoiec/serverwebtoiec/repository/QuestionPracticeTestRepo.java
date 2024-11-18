package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.PracticeTest;
import com.webtoiec.serverwebtoiec.entities.QuestionPracticeTest;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionPracticeTestRepo extends JpaRepository<QuestionPracticeTest, Integer> {

  List<QuestionPracticeTest> findByPracticetest(PracticeTest practicetest);
  Page<QuestionPracticeTest> findByPracticetest(Pageable pageable, PracticeTest practicetest);

}
