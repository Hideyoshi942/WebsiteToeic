package com.webtoiec.serverwebtoiec.service;

import com.webtoiec.serverwebtoiec.entities.CommentGrammar;
import com.webtoiec.serverwebtoiec.entities.Grammar;
import com.webtoiec.serverwebtoiec.repository.CommentGrammarRepo;
import com.webtoiec.serverwebtoiec.service.impl.CommentGrammarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentGrammarImpl implements CommentGrammarService {
  @Autowired
  CommentGrammarRepo commentGrammarRepo;

  @Override
  public List<CommentGrammar> findByGramamar(Grammar grammar) {
    return commentGrammarRepo.findByGrammar(grammar);
  }

  @Override
  public void save(CommentGrammar commentGrammar) {
    commentGrammarRepo.save(commentGrammar);
  }
}
