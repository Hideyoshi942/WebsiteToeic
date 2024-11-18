package com.webtoiec.serverwebtoiec.api;

import com.webtoiec.serverwebtoiec.entities.Role;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.service.impl.SecurityService;
import com.webtoiec.serverwebtoiec.service.impl.UserService;
import com.webtoiec.serverwebtoiec.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterAccount {

  @Autowired
  private UserService userService;

  @Autowired
  private SecurityService securityService;

  @Autowired
  private UserValidator userValidator;

  @PostMapping
  public String registerAccount(@RequestBody UserToeic user, BindingResult bindingResult) {
    userValidator.validate(user, bindingResult);
    if (bindingResult.hasErrors()) {
      return bindingResult.getAllErrors().toString();
    }
    user.setRole(Role.ROLE_MEMBER);
    userService.saveUser(user);
    return "Success register account";
  }
}
