package com.webtoiec.serverwebtoiec.service;

import com.webtoiec.serverwebtoiec.entities.ResultTest;
import com.webtoiec.serverwebtoiec.repository.ResultTestRepo;
import com.webtoiec.serverwebtoiec.service.impl.ResultTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultTestImpl implements ResultTestService {
  @Autowired
  ResultTestRepo resultTestRepo;

  @Override
  public void save(ResultTest resulttest) {
    resultTestRepo.save(resulttest);
  }
}
