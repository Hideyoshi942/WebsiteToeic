package com.webtoiec.serverwebtoiec.service.impl;

import com.webtoiec.serverwebtoiec.entities.Role;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private AuthenticationManager authManager;

  private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

  public UserToeic findByEmail(String email) {
    return userRepo.findByEmail(email);
  }

  public UserToeic saveUser(UserToeic user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    return userRepo.save(user);
  }

  public UserToeic findById (int id)
  {
    UserToeic user = userRepo.findById(id).get();
    return user;
  }

  public UserToeic findByUsername(String username) {
    return userRepo.findByUsername(username);
  }

  public UserToeic updateUser(UserToeic user) {
    return userRepo.save(user);
  }

  public void changePass(UserToeic user, String newPass) {
    user.setPassword(bCryptPasswordEncoder.encode(newPass));
    userRepo.save(user);
  }

  public Page<UserToeic> findByRole(int page, Role role)
  {
    return userRepo.findByRole(role, PageRequest.of(page - 1 , 6));
  }

  public void deleteById(int id) {
    userRepo.deleteById(id);
  }
}
