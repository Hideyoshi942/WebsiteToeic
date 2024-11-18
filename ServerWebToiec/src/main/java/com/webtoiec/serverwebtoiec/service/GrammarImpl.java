package com.webtoiec.serverwebtoiec.service;

import com.webtoiec.serverwebtoiec.entities.Grammar;
import com.webtoiec.serverwebtoiec.repository.GrammarRepo;
import com.webtoiec.serverwebtoiec.service.impl.GrammarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GrammarImpl implements GrammarService {
  @Autowired
  GrammarRepo grammarRepo;

  @Override
  public void save(Grammar grammar) {
    grammarRepo.save(grammar);
  }

  @Override
  public List<Grammar> getGrammar(int grammarid) {
    return grammarRepo.findBygrammarid(grammarid);
  }

  @Override
  public Page<Grammar> getGrammar(int page, int size) {
    return grammarRepo.findAll(PageRequest.of(page, size));
  }

  @Override
  public List<Grammar> getAllGrammar() {
    return grammarRepo.findAll();
  }

  @Override
  public void delete(int grammarid) {
    grammarRepo.deleteById(grammarid);
  }

  @Override
  public List<Grammar> searchListGrammar(String search) {
    return grammarRepo.searchGrammar(search);
  }
}
