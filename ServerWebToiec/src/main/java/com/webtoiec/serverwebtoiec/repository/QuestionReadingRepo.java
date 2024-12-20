package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.QuestionReading;
import com.webtoiec.serverwebtoiec.entities.Reading;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionReadingRepo extends JpaRepository<QuestionReading, Integer> {
  Page<QuestionReading> findByquestionreadingid(int questionreadingid, Pageable pageable);
  List<QuestionReading> findByquestionreadingid(int questionreadingid);

  List<QuestionReading> findByReading(Reading reading);
}
