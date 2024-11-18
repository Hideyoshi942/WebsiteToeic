package com.webtoiec.serverwebtoiec.service.impl;

import com.webtoiec.serverwebtoiec.entities.QuestionVocabulary;
import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import com.webtoiec.serverwebtoiec.repository.QuestionVocabularyRepo;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionVocabularyService {
  @Autowired
  private QuestionVocabularyRepo questionVocabularyRepo;

  public Page<QuestionVocabulary> findListQuestionVocabularyId(int page, int size, int vocabularyid) {
    return questionVocabularyRepo.findByquestionvocabularyid(vocabularyid, PageRequest.of(page - 1, size));
  }

  public List<QuestionVocabulary> findListQuestionVocabularyId(int vocabularyid) {
    return questionVocabularyRepo.findByquestionvocabularyid(vocabularyid);
  }

  public Page<QuestionVocabulary> findListQuestionByVocabularyId(int page, int size, Vocabulary vocabulary) {
    return questionVocabularyRepo.findByvocabulary(vocabulary, PageRequest.of(page - 1, size));
  }

  public List<QuestionVocabulary> findListQuestionByVocabularyId(Vocabulary vocabulary) {
    return questionVocabularyRepo.findByvocabulary(vocabulary);
  }

  public Optional<QuestionVocabulary> findQuestionVocabularyById(int id) {
    return questionVocabularyRepo.findById(id);
  }

  @Transactional
  public QuestionVocabulary saveQuestionVocabulary(QuestionVocabulary questionVocabulary, HttpServletRequest request) throws IOException {
    return questionVocabularyRepo.save(questionVocabulary);
  }

  public QuestionVocabulary save(QuestionVocabulary questionVocabulary) {
    return questionVocabularyRepo.save(questionVocabulary);
  }

  public void deleteQuestionVocabulary(int id) {
    questionVocabularyRepo.deleteById(id);
  }
}
