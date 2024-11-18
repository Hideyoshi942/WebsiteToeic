package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.Listening;
import com.webtoiec.serverwebtoiec.entities.QuestionListening;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionListeningRepo extends JpaRepository<QuestionListening, Integer> {
  Page<QuestionListening> findByquestionlisteningid(int questionlisteningid, Pageable pageable);

  List<QuestionListening> findByquestionlisteningid(int questionlisteningid);

  List<QuestionListening> findByListening(Listening listening);
}
