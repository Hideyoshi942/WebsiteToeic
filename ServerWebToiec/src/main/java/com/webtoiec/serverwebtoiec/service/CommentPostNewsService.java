package com.webtoiec.serverwebtoiec.service;

import com.webtoiec.serverwebtoiec.repository.CommentPostNewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentPostNewsService {
  @Autowired
  private CommentPostNewsRepo commentPostNewsRepo;

}
