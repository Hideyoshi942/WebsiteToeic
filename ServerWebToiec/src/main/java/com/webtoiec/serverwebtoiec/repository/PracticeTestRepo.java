package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.PracticeTest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeTestRepo extends JpaRepository<PracticeTest, Integer> {
  List<PracticeTest> findBypracticetestid(int practicetestid);
}
