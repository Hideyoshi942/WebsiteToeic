package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.Grammar;
import com.webtoiec.serverwebtoiec.entities.GrammarLessonContent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrammarLessonContentRepo extends JpaRepository<GrammarLessonContent, Integer> {
  List<GrammarLessonContent> findByGrammar(Grammar grammar);
}
