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
@Table(name = "listening")
public class Listening {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "listeningid", nullable = false)
  private int listeningid;

  @Column(name = "listeninglevel")
  private int listeninglevel;

  @Column(name = "listeningpart")
  private int listeningpart;

  @Column(name = "listeningscript")
  private String listeningscript;

  @Column(name = "listeningname")
  private String listeningname;

  @Transient
//  @JsonIgnore
  private MultipartFile listeningimage;

  @Transient
//  @JsonIgnore
  private MultipartFile listeningaudio;

  @Transient
//  @JsonIgnore
  private MultipartFile listeningfileExcelQuestion;

  public Listening() {
  }

  @Override
  public String toString() {
    return "Listening{" +
        "listeningid=" + listeningid +
        ", listeninglevel=" + listeninglevel +
        ", listeningpart=" + listeningpart +
        ", listeningscript='" + listeningscript + '\'' +
        ", listeningname='" + listeningname + '\'' +
        ", listeningimage=" + listeningimage +
        ", listeningaudio=" + listeningaudio +
        ", listeningfileExcelQuestion=" + listeningfileExcelQuestion +
        '}';
  }

  public int getListeningid() {
    return listeningid;
  }

  public void setListeningid(int listeningid) {
    this.listeningid = listeningid;
  }

  public int getListeninglevel() {
    return listeninglevel;
  }

  public void setListeninglevel(int listeninglevel) {
    this.listeninglevel = listeninglevel;
  }

  public int getListeningpart() {
    return listeningpart;
  }

  public void setListeningpart(int listeningpart) {
    this.listeningpart = listeningpart;
  }

  public String getListeningscript() {
    return listeningscript;
  }

  public void setListeningscript(String listeningscript) {
    this.listeningscript = listeningscript;
  }

  public String getListeningname() {
    return listeningname;
  }

  public void setListeningname(String listeningname) {
    this.listeningname = listeningname;
  }

  public MultipartFile getListeningimage() {
    return listeningimage;
  }

  public void setListeningimage(MultipartFile listeningimage) {
    this.listeningimage = listeningimage;
  }

  public MultipartFile getListeningaudio() {
    return listeningaudio;
  }

  public void setListeningaudio(MultipartFile listeningaudio) {
    this.listeningaudio = listeningaudio;
  }

  public MultipartFile getListeningfileExcelQuestion() {
    return listeningfileExcelQuestion;
  }

  public void setListeningfileExcelQuestion(
      MultipartFile listeningfileExcelQuestion) {
    this.listeningfileExcelQuestion = listeningfileExcelQuestion;
  }

  public Listening(int listeningid, int listeninglevel, int listeningpart, String listeningscript,
      String listeningname, MultipartFile listeningimage, MultipartFile listeningaudio,
      MultipartFile listeningfileExcelQuestion) {
    this.listeningid = listeningid;
    this.listeninglevel = listeninglevel;
    this.listeningpart = listeningpart;
    this.listeningscript = listeningscript;
    this.listeningname = listeningname;
    this.listeningimage = listeningimage;
    this.listeningaudio = listeningaudio;
    this.listeningfileExcelQuestion = listeningfileExcelQuestion;
  }
}
