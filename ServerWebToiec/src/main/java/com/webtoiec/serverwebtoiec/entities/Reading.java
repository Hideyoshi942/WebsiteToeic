package com.webtoiec.serverwebtoiec.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.List;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "reading")
public class Reading {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int readingid;

  @Column(name = "readinglevel")
  private int readinglevel;

  @Column(name = "readingpart")
  private int readingpart;

  private String readingscript;

  private String readingname;

  @Transient
  @JsonIgnore
  private MultipartFile readingimage;

  @Transient
  @JsonIgnore
  private MultipartFile readingfileExcelQuestion;

  public Reading() {
  }

  @Override
  public String toString() {
    return "Reading{" +
        "readingid=" + readingid +
        ", readinglevel=" + readinglevel +
        ", readingpart=" + readingpart +
        ", readingscript='" + readingscript + '\'' +
        ", readingname='" + readingname + '\'' +
        ", readingimage=" + readingimage +
        ", readingfileExcelQuestion=" + readingfileExcelQuestion +
        '}';
  }

  public int getReadingid() {
    return readingid;
  }

  public void setReadingid(int readingid) {
    this.readingid = readingid;
  }

  public int getReadinglevel() {
    return readinglevel;
  }

  public void setReadinglevel(int readinglevel) {
    this.readinglevel = readinglevel;
  }

  public int getReadingpart() {
    return readingpart;
  }

  public void setReadingpart(int readingpart) {
    this.readingpart = readingpart;
  }

  public String getReadingscript() {
    return readingscript;
  }

  public void setReadingscript(String readingscript) {
    this.readingscript = readingscript;
  }

  public String getReadingname() {
    return readingname;
  }

  public void setReadingname(String readingname) {
    this.readingname = readingname;
  }

  public MultipartFile getReadingimage() {
    return readingimage;
  }

  public void setReadingimage(MultipartFile readingimage) {
    this.readingimage = readingimage;
  }

  public MultipartFile getReadingfileExcelQuestion() {
    return readingfileExcelQuestion;
  }

  public void setReadingfileExcelQuestion(
      MultipartFile readingfileExcelQuestion) {
    this.readingfileExcelQuestion = readingfileExcelQuestion;
  }

  public Reading(int readingid, int readinglevel, int readingpart, String readingscript,
      String readingname, MultipartFile readingimage, MultipartFile readingfileExcelQuestion) {
    this.readingid = readingid;
    this.readinglevel = readinglevel;
    this.readingpart = readingpart;
    this.readingscript = readingscript;
    this.readingname = readingname;
    this.readingimage = readingimage;
    this.readingfileExcelQuestion = readingfileExcelQuestion;
  }
}
