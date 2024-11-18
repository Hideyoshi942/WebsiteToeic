package com.webtoiec.serverwebtoiec.service;

import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import com.webtoiec.serverwebtoiec.entities.VocabularyLessonContent;
import com.webtoiec.serverwebtoiec.repository.VocabularyLessonContentRepo;
import com.webtoiec.serverwebtoiec.service.impl.VocabularyLessonContentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VocabularyLessonContentImpl implements VocabularyLessonContentService {
  @Autowired
  VocabularyLessonContentRepo vocabularyLessonContentRepo;


  @Override
  public void save(VocabularyLessonContent vocabularyLessonContent) {
    vocabularyLessonContentRepo.save(vocabularyLessonContent);
  }

  @Override
  public void delete(int vocabularylessoncontentid) {
    vocabularyLessonContentRepo.deleteById(vocabularylessoncontentid);
  }

  @Override
  public List<VocabularyLessonContent> findByVocabulary(Vocabulary vocabulary) {
    return vocabularyLessonContentRepo.findByVocabulary(vocabulary);
  }
}
