package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.CommentGrammar;
import com.webtoiec.serverwebtoiec.entities.Grammar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentGrammarRepo extends JpaRepository<CommentGrammar, Integer> {
  List<CommentGrammar> findByGrammar(Grammar grammar);
}
