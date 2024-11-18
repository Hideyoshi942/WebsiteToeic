package com.webtoiec.serverwebtoiec.service.impl;

import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import com.webtoiec.serverwebtoiec.entities.VocabularyLessonContent;
import java.util.List;

public interface VocabularyLessonContentService {
  void save(VocabularyLessonContent vocabularyLessonContent);
  void delete(int vocabularylessoncontentid);

  List<VocabularyLessonContent> findByVocabulary(Vocabulary vocabulary);
}
