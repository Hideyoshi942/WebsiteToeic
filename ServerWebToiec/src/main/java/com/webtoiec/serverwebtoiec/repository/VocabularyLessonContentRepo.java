package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import com.webtoiec.serverwebtoiec.entities.VocabularyLessonContent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface VocabularyLessonContentRepo extends JpaRepository<VocabularyLessonContent, Integer> {
  List<VocabularyLessonContent> findByVocabulary(Vocabulary vocabulary);
}
