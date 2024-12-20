package com.webtoiec.serverwebtoiec.validator;

import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.service.impl.UserService;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

  @Autowired
  private UserService userService;

  @Override
  public boolean supports(Class<?> clazz)
  {
    return UserToeic.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors)
  {
    UserToeic user = (UserToeic) target;

    ValidationUtils.rejectIfEmpty(errors, "username", "error.username", "Username is required");
    ValidationUtils.rejectIfEmpty(errors, "phonenumber", "error.phonenumber", "Phone number is required");
    ValidationUtils.rejectIfEmpty(errors, "address", "error.address", "Address is required");

    // Validate for email
    // Required field
    ValidationUtils.rejectIfEmpty(errors, "email", "error.email", "Email is required");

    // Check form email
    Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    if (!(pattern.matcher(user.getEmail()).matches())) {
      errors.rejectValue("email", "error.email", "Email is invalid");
    }

    // Check email already exists
    if (userService.findByEmail(user.getEmail()) != null) {
      errors.rejectValue("email", "error.email", "Email is already exists");
    }

    // Check password is empty
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "Password is required");

    // Check confirm password is empty
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comfirmPassword", "error.comfirmPassword", "Confirm password is required");

    // Check confirm password is match
    if (!user.getPassword().equals(user.getComfirmPassword())) {
      errors.rejectValue("comfirmPassword", "error.comfirmPassword", "Confirm password is not match");
    }

    // Check length password
    if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
      errors.rejectValue("password", "error.password",
          "Password must be between 8 and 32 characters");
    }

    // Check match pass and confirm pass
    if (!user.getPassword().equals(user.getComfirmPassword())) {
      errors.rejectValue("comfirmPassword", "error.comfirmPassword",
          "Confirm password is not match");
    }
  }
}
