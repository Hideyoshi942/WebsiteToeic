package com.webtoiec.serverwebtoiec.api.admin;

import com.webtoiec.serverwebtoiec.dto.PasswordDTO;
import com.webtoiec.serverwebtoiec.dto.ResponseObject;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.repository.UserRepo;
import com.webtoiec.serverwebtoiec.service.SendEmailService;
import com.webtoiec.serverwebtoiec.service.impl.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/profile")
public class ProfileAPI {
  @Autowired
  private UserService userService;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private SendEmailService sendEmailService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @GetMapping("/{id}")
  public UserToeic getUserToeicById(@PathVariable int id) {
    UserToeic user = userService.findById(id);
    return user;
  }

  @PutMapping("/update/{id}")
  public String updateUser(@PathVariable int id, @RequestBody UserToeic updatedUser) {
    // Tìm kiếm người dùng theo ID
    UserToeic existingUser = userRepo.findById(id).orElseThrow(() -> new ExecutionException("User not found"));

    // Cập nhật thông tin nếu người dùng cung cấp giá trị mới
    if (updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty()) {
      existingUser.setUsername(updatedUser.getUsername());
    }
    if (updatedUser.getPhonenumber() != null && !updatedUser.getPhonenumber().isEmpty()) {
      existingUser.setPhonenumber(updatedUser.getPhonenumber());
    }
    if (updatedUser.getAddress() != null && !updatedUser.getAddress().isEmpty()) {
      existingUser.setAddress(updatedUser.getAddress());
    }

    // Lưu lại người dùng đã cập nhật
    userRepo.save(existingUser);
    return "Cập nhật thông tin thành công";
  }


  @PutMapping("/changePass/{id}")
  public String changePass(@PathVariable ("id") int id,
      @RequestParam("oldPassword") String oldPassword,
      @RequestParam("newPassword") String newPassword,
      @RequestParam("comfirmPassword") String comfirmPassword
      ) {
    UserToeic userToeic = userRepo.findById(id).orElse(null);

    if (userToeic == null) {
      // Nếu người dùng không tồn tại, trả về lỗi 404
      return "Không tìm thấy người dùng với id: " + id;
    }
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    if (passwordEncoder.matches(oldPassword, userToeic.getPassword())) {
      if (newPassword.equals(comfirmPassword)) {
        userToeic.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(userToeic);
      }
    }
    return "Đổi mật khẩu thành công";
  }

  @PutMapping("/forgetPass/{email}")
  public String forgetPasswordWithEmail(@PathVariable("email") String email)
  {
    UserToeic userToeic = userRepo.findByEmail(email);
    if (userToeic == null) {
      // Nếu người dùng không tồn tại, trả về lỗi 404
      return "Không tìm thấy người dùng với email: " + email;
    }
    String randomPassword = generatePassword();
    userToeic.setPassword(passwordEncoder.encode(randomPassword));
    String subject = "Email cấp lại mật khẩu";
    String body = "<html>" +
        "<body>" +
        "<h3>Email cấp lại mật khẩu</h3>" +
        "<p>Xin chào " + userToeic.getUsername() + ",</p>" +
        "<p>Chúng tôi nhận được yêu cầu cấp lại mật khẩu của bạn.</p>" +
        "<p>Mật khẩu cấp lại của bạn là: <strong style=\"color: blue;\">" + randomPassword + "</strong></p>" +
        "<p>Vui lòng không cung cấp mật khẩu cho bất kì ai!</p>" +
        "<p style=\"color:red;\">Cảm ơn bạn vì đã sử dụng dịch vụ của chúng tôi!</p>" +
        "</body>" +
        "</html>";

    userRepo.save(userToeic);
    sendEmailService.sendEmail(email, body, subject);
    return "Đã gửi email thành công";

  }


  // Password generated
  public static String generatePassword() {
    // Các chuỗi chứa ký tự chữ hoa, chữ thường, số và ký tự đặc biệt
    String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    String DIGITS = "0123456789";
    String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+<>?";

    // Tạo đối tượng SecureRandom để đảm bảo mật khẩu ngẫu nhiên và bảo mật
    SecureRandom random = new SecureRandom();

    // Chọn ít nhất 1 ký tự từ mỗi loại
    char upperCase = UPPERCASE.charAt(random.nextInt(UPPERCASE.length()));
    char lowerCase = LOWERCASE.charAt(random.nextInt(LOWERCASE.length()));
    char digit = DIGITS.charAt(random.nextInt(DIGITS.length()));
    char specialChar = SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length()));

    // Tạo một chuỗi kết hợp từ các ký tự đã chọn
    StringBuilder password = new StringBuilder();
    password.append(upperCase)
        .append(lowerCase)
        .append(digit)
        .append(specialChar);

    // Thêm các ký tự ngẫu nhiên từ tất cả các loại để đảm bảo mật khẩu có ít nhất 8 ký tự
    String allCharacters = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;
    for (int i = password.length(); i < 8; i++) {
      password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
    }

    // Trộn lại các ký tự trong password để mật khẩu trở nên ngẫu nhiên hơn
    char[] arr = password.toString().toCharArray();
    for (int i = arr.length - 1; i > 0; i--) {
      int j = random.nextInt(i + 1);
      char temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
    }

    return new String(arr);
  }
}
