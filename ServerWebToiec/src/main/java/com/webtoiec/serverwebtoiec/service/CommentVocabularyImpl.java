package com.webtoiec.serverwebtoiec.service;

import com.webtoiec.serverwebtoiec.entities.CommentVocabulary;
import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import com.webtoiec.serverwebtoiec.repository.CommentVocabularyRepo;
import com.webtoiec.serverwebtoiec.service.impl.CommentVocabularyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentVocabularyImpl implements CommentVocabularyService {

  @Autowired
  CommentVocabularyRepo commentVocabularyRepo;


  @Override
  public List<CommentVocabulary> findByVocabulary(Vocabulary vocabulary) {
    return commentVocabularyRepo.findByVocabulary(vocabulary);
  }

  @Override
  public void save(CommentVocabulary commentVocabulary) {
    commentVocabularyRepo.save(commentVocabulary);
  }
}
