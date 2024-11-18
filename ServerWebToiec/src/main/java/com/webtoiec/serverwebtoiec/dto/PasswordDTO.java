package com.webtoiec.serverwebtoiec.dto;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class PasswordDTO {
  @NotEmpty(message = "Old password is required")
  private String oldPassword;

  @NotEmpty(message = "New password is required")
  @Length(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
  private String newPassword;

  @NotEmpty(message = "Confirm password is required")
  private String comfirmNewPassword;

  public String getOldPassword() {
    return oldPassword;
  }
  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }
  public String getNewPassword() {
    return newPassword;
  }
  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
  public String getComfirmNewPassword() {
    return comfirmNewPassword;
  }
  public void setComfirmNewPassword(String comfirmNewPassword) {
    this.comfirmNewPassword = comfirmNewPassword;
  }

  @Override
  public String toString() {
    return "PasswordDTO{" +
        "oldPassword='" + oldPassword + '\'' +
        ", newPassword='" + newPassword + '\'' +
        ", comfirmNewPassword='" + comfirmNewPassword + '\'' +
        '}';
  }
}
