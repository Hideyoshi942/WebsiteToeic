package com.webtoiec.serverwebtoiec.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class AccountDTO {
  private String id;

  @NotEmpty(message = "Email is required")
  @Email(message = "Email is valid required")
  private String email;

  @Length(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
  private String password;

  private String confirmPassword;

  @NotEmpty(message = "Address is required")
  private String address;

  @NotEmpty(message = "Username is required")
  private String username;

  @NotEmpty(message = "Phonenumber is required")
  private String phonenumber;

  private int role;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public @NotEmpty(message = "Email is required") @Email(message = "Email is valid required") String getEmail() {
    return email;
  }

  public void setEmail(
      @NotEmpty(message = "Email is required") @Email(message = "Email is valid required") String email) {
    this.email = email;
  }

  public @Length(min = 8, max = 32, message = "Password must be between 8 and 32 characters") String getPassword() {
    return password;
  }

  public void setPassword(
      @Length(min = 8, max = 32, message = "Password must be between 8 and 32 characters") String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public @NotEmpty(message = "Address is required") String getAddress() {
    return address;
  }

  public void setAddress(
      @NotEmpty(message = "Address is required") String address) {
    this.address = address;
  }

  public @NotEmpty(message = "Username is required") String getUsername() {
    return username;
  }

  public void setUsername(
      @NotEmpty(message = "Username is required") String username) {
    this.username = username;
  }

  public @NotEmpty(message = "Phonenumber is required") String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(
      @NotEmpty(message = "Phonenumber is required") String phonenumber) {
    this.phonenumber = phonenumber;
  }

  public int getRole() {
    return role;
  }

  public void setRole(int role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "AccountDTO{" +
        "id='" + id + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", confirmPassword='" + confirmPassword + '\'' +
        ", address='" + address + '\'' +
        ", username='" + username + '\'' +
        ", phonenumber='" + phonenumber + '\'' +
        ", role=" + role +
        '}';
  }
}
