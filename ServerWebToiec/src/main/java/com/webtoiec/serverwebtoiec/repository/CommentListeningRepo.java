package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.CommentListening;
import com.webtoiec.serverwebtoiec.entities.CommentVocabulary;
import com.webtoiec.serverwebtoiec.entities.Listening;
import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentListeningRepo extends JpaRepository<CommentListening, Integer> {
  List<CommentListening> findByListening(Listening listening);
}
