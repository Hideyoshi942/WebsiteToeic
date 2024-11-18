package com.webtoiec.serverwebtoiec.service.impl;

import com.webtoiec.serverwebtoiec.entities.CommentGrammar;
import com.webtoiec.serverwebtoiec.entities.Grammar;
import java.util.List;

public interface CommentGrammarService {
  List<CommentGrammar> findByGramamar(Grammar grammar);
  void save(CommentGrammar commentGrammar);
}
