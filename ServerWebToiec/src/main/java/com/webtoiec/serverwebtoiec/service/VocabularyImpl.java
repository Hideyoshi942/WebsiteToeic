package com.webtoiec.serverwebtoiec.service;

import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import com.webtoiec.serverwebtoiec.repository.VocabularyRepo;
import com.webtoiec.serverwebtoiec.service.impl.VocabularyService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class VocabularyImpl implements VocabularyService {
  @Autowired
  VocabularyRepo vocabularyRepo;

  @Override
  public List<Vocabulary> findAll() {
    return vocabularyRepo.findAll();
  }

  @Override
  public void save(Vocabulary vocabulary) {
    vocabularyRepo.save(vocabulary);
  }

  @Override
  public void delete(int vocabularyid) {
    vocabularyRepo.deleteById(vocabularyid);
  }

  @Override
  public Page<Vocabulary> getVocabulary(int page, int size) {
    return vocabularyRepo.findAll(PageRequest.of(page, size));
  }

  @Override
  public Optional<Vocabulary> getVocabularyByVocabularyid(int vocabularyid) {
    return vocabularyRepo.findById(vocabularyid);
  }

  @Override
  public List<Vocabulary> getVocabulary(int vocabularyid) {
    return vocabularyRepo.findByvocabularyid(vocabularyid);
  }

  @Override
  public List<Vocabulary> searchListVocabulary(String search) {
    return vocabularyRepo.searchVocabulary(search);
  }


}
