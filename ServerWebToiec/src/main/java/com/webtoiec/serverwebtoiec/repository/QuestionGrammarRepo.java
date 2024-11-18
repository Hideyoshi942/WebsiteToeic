package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.Grammar;
import com.webtoiec.serverwebtoiec.entities.QuestionGrammar;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionGrammarRepo extends JpaRepository<QuestionGrammar, Integer> {
  Page<QuestionGrammar> findByquestiongrammarid(int questiongrammarid, Pageable pageable);
  List<QuestionGrammar> findByquestiongrammarid(int questiongrammarid);

  List<QuestionGrammar> findByGrammar(Grammar grammar);
}
