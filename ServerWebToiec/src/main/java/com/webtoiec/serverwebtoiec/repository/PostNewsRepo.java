package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.PostNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostNewsRepo extends JpaRepository<PostNews, Integer> {

}
