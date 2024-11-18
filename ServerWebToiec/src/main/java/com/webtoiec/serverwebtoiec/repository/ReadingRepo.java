package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.Reading;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepo extends JpaRepository<Reading, Integer>, QuerydslPredicateExecutor<Reading> {
  Page<Reading> findByReadingpartAndReadinglevel(int readingpart, int readingLevel, Pageable pageable);
}

