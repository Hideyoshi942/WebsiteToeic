package com.webtoiec.serverwebtoiec.service;

import com.webtoiec.serverwebtoiec.entities.DetailResultExam;
import com.webtoiec.serverwebtoiec.entities.ResultTest;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.repository.DetailResultExamRepo;
import com.webtoiec.serverwebtoiec.repository.ResultTestRepo;
import com.webtoiec.serverwebtoiec.repository.UserRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailResultExamService {
  @Autowired
  private DetailResultExamRepo detailResultExamRepo;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private ResultTestRepo resultTestRepo;

}
