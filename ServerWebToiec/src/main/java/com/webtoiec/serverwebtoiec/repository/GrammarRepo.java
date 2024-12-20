package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.Grammar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GrammarRepo extends JpaRepository<Grammar, Integer> {
  List<Grammar> findBygrammarid(int grammarid);

  @Query("select grammar from Grammar grammar where grammar.grammarname like concat('%', :search, '%') ")
  List<Grammar> searchGrammar(@Param("search") String search);
}
