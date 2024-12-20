package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.Listening;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ListeningRepo extends JpaRepository<Listening, Integer>, QuerydslPredicateExecutor<Listening> {
  Page<Listening> findByListeningpartAndListeninglevel(int listeningpart, int listeningLevel, Pageable pageable);
}

