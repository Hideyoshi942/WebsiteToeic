package com.webtoiec.serverwebtoiec.config;

import com.webtoiec.serverwebtoiec.entities.Role;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;


  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    // Admin account
    if (userRepo.findByEmail("admin@gmail.com") == null) {
      UserToeic admin = new UserToeic();
      admin.setEmail("admin@gmail.com");
      admin.setPassword(bCryptPasswordEncoder.encode("123456"));
      admin.setUsername("admin");
      admin.setPhonenumber("0123456789");
      admin.setRole(Role.ROLE_ADMIN);
      userRepo.save(admin);
    }

    // Member account
    if (userRepo.findByEmail("member@gmail.com") == null) {
      UserToeic member = new UserToeic();
      member.setEmail("member@gmail.com");
      member.setPassword(bCryptPasswordEncoder.encode("123456"));
      member.setUsername("member");
      member.setPhonenumber("0123456789");
      member.setRole(Role.ROLE_MEMBER);
      userRepo.save(member);
    }
  }

}
