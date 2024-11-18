package com.webtoiec.serverwebtoiec.service.impl;

import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface VocabularyService {
  List<Vocabulary> findAll();

  void save(Vocabulary vocabulary);

  void delete(int vocabularyid);

  Page<Vocabulary> getVocabulary(int page, int limit);

  Optional<Vocabulary> getVocabularyByVocabularyid(int vocabularyid);

  List<Vocabulary> getVocabulary(int vocabularyid);

  List<Vocabulary> searchListVocabulary(String search);
}
