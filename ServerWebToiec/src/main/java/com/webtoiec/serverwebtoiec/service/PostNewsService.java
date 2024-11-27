package com.webtoiec.serverwebtoiec.service;

import com.webtoiec.serverwebtoiec.repository.PostNewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostNewsService {
  @Autowired
  private PostNewsRepo postNewsRepo;
}
