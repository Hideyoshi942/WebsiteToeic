package com.webtoiec.serverwebtoiec.api.admin;

import com.webtoiec.serverwebtoiec.dto.AccountDTO;
import com.webtoiec.serverwebtoiec.dto.ResponseObject;
import com.webtoiec.serverwebtoiec.entities.Role;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.service.impl.UserService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/account")
public class AccountAPI {
  @Autowired
  private UserService userService;

  private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

  @GetMapping("/all")
  public Page<UserToeic> getAllUsers(@RequestParam(value = "role", defaultValue = "1") int roleValue, @RequestParam(defaultValue = "1") int page) {
    return userService.findByRole(page, Role.findByAbbr(roleValue));
  }

  @PostMapping("/save")
  public ResponseObject saveAccount(@RequestBody @Valid AccountDTO accountDTO, BindingResult result, Model model) {
    ResponseObject responseObject = new ResponseObject();

    if (userService.findByEmail(accountDTO.getEmail()) != null) {
      result.rejectValue("email", "error.email", "Email đã tồn tại");
    }

    if (!accountDTO.getConfirmPassword().equals(accountDTO.getPassword())) {
      result.rejectValue("confirmPassword", "error.confirmPassword", "Xác nhận mật khẩu không đúng");
    }

    if (result.hasErrors()) {
      setErrorsForResponseObject(result, responseObject);
    } else {
      responseObject.setStatus("Success");
      UserToeic user = new UserToeic();
      user.setEmail(accountDTO.getEmail());
      user.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
      user.setAddress(accountDTO.getAddress());
      user.setUsername(accountDTO.getUsername());
      user.setPhonenumber(accountDTO.getPhonenumber());
      user.setRole(Role.findByAbbr(accountDTO.getRole()));
      userService.saveUser(user);
    }
    return responseObject;
  }

  @DeleteMapping("/delete/{id}")
  public void deleteAccount(@PathVariable("id") int id) {
    userService.deleteById(id);
  }

  public void setErrorsForResponseObject(BindingResult result, ResponseObject responseObject) {
    Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(
        FieldError::getField, FieldError::getDefaultMessage));
    responseObject.setErrorMessage(errors);
    responseObject.setStatus("Error");
    List<String> keys = new ArrayList<String>(errors.keySet());
    errors = null;
  }
}
