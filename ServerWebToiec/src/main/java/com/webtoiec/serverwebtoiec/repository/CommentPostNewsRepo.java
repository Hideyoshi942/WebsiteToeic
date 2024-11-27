package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.CommentPostNews;
import com.webtoiec.serverwebtoiec.entities.PostNews;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentPostNewsRepo extends JpaRepository<CommentPostNews, Integer> {

  List<CommentPostNews> findByPostnews(PostNews postNews);
}
