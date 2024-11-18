package com.webtoiec.serverwebtoiec.api;

import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.service.JWTService;
import com.webtoiec.serverwebtoiec.service.impl.SecurityService;
import com.webtoiec.serverwebtoiec.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginAccount {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<UserToeic> loginAccount(@RequestBody UserToeic user) {
    UserToeic userToeic = new UserToeic();
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
    );

    if (authentication.isAuthenticated()) {
      userToeic = userService.findByEmail(user.getEmail());
    }
    return ResponseEntity.ok(userToeic);
  }
}
