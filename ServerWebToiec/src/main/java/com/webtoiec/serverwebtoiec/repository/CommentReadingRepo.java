package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.CommentReading;
import com.webtoiec.serverwebtoiec.entities.Reading;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CommentReadingRepo extends JpaRepository<CommentReading, Integer> {
  List<CommentReading> findByReading(Reading reading);
}
