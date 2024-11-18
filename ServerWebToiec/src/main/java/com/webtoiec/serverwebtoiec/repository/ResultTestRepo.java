package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.ResultTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultTestRepo extends JpaRepository<ResultTest, Integer> {

}
