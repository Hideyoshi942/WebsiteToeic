package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.CommentVocabulary;
import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CommentVocabularyRepo extends JpaRepository<CommentVocabulary, Integer> {
  List<CommentVocabulary> findByVocabulary(Vocabulary vocabulary);

}
