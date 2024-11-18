package com.webtoiec.serverwebtoiec.service.impl;

import com.webtoiec.serverwebtoiec.entities.CommentVocabulary;
import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import java.util.List;

public interface CommentVocabularyService {
  List<CommentVocabulary> findByVocabulary(Vocabulary vocabulary);

  void save(CommentVocabulary commentVocabulary);
}
