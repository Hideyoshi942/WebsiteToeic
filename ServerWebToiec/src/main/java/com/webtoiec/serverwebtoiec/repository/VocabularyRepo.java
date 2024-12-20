package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VocabularyRepo extends JpaRepository<Vocabulary, Integer> {
  List<Vocabulary> findByvocabularyid(int vocabularyid);

  @Query("select vocabulary from Vocabulary vocabulary where vocabulary.vocabularyname like concat('%', :search, '%') ")
  List<Vocabulary> searchVocabulary(@Param("search") String search);
}
