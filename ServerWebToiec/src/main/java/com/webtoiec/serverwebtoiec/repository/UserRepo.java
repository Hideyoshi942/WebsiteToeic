package com.webtoiec.serverwebtoiec.repository;

import com.webtoiec.serverwebtoiec.entities.Role;
import com.webtoiec.serverwebtoiec.entities.UserToeic;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserToeic, Integer> {
  UserToeic findByUsername(String username);

  UserToeic findByEmail(String email);

  Page<UserToeic> findByRole(Role role, Pageable of);

  List<UserToeic> findByRole(Role role);
}
