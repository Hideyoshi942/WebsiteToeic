package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.DetailResultExam;
import com.webtoiec.serverwebtoiec.entities.ResultTest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailResultExamRepo extends JpaRepository<DetailResultExam, Integer> {
  List<DetailResultExam> findAllByresultTest(ResultTest resultTest);
}
