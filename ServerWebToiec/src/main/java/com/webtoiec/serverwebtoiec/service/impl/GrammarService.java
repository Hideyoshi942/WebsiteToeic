package com.webtoiec.serverwebtoiec.service.impl;

import com.webtoiec.serverwebtoiec.entities.Grammar;
import java.util.List;
import org.springframework.data.domain.Page;

public interface GrammarService {
  void save(Grammar grammar);

  List<Grammar> getGrammar(int grammarid);

  Page<Grammar> getGrammar(int page, int limit);

  List<Grammar> getAllGrammar();

  void delete(int grammarid);

  List<Grammar> searchListGrammar(String search);
}
