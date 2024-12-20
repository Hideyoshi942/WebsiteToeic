package com.webtoiec.serverwebtoiec.entities;

import com.webtoiec.serverwebtoiec.jpautil.RoleTypeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name = "usertoeic")
public class UserToeic {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String username;

  @JsonIgnore
  private String password;

  @Transient
  @JsonIgnore
  private String comfirmPassword;

  private String email;

  private String phonenumber;

  private String address;

  @Convert(converter = RoleTypeConverter.class)
  private Role role;

  public UserToeic() {
  }

  public UserToeic(int id, String username, String password, String comfirmPassword, String email,
      String phonenumber, String address, Role role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.comfirmPassword = comfirmPassword;
    this.email = email;
    this.phonenumber = phonenumber;
    this.address = address;
    this.role = role;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getComfirmPassword() {
    return comfirmPassword;
  }

  public void setComfirmPassword(String comfirmPassword) {
    this.comfirmPassword = comfirmPassword;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "UserToeic{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", comfirmPassword='" + comfirmPassword + '\'' +
        ", email='" + email + '\'' +
        ", phonenumber='" + phonenumber + '\'' +
        ", address='" + address + '\'' +
        ", role=" + role +
        '}';
  }
}
