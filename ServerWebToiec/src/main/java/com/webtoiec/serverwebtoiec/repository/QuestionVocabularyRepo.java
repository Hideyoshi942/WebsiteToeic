package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.QuestionVocabulary;
import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface QuestionVocabularyRepo extends JpaRepository<QuestionVocabulary, Integer> {
  Page<QuestionVocabulary> findByquestionvocabularyid(int questionvocabularyid, Pageable pageable);
  List<QuestionVocabulary> findByquestionvocabularyid(int questionvocabularyid);

  Page<QuestionVocabulary> findByvocabulary(Vocabulary vocabulary, Pageable pageable);
  List<QuestionVocabulary> findByvocabulary(Vocabulary vocabulary);
}
