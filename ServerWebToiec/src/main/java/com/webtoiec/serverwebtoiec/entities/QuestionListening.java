package com.webtoiec.serverwebtoiec.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.Arrays;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name = "questionlistening")
public class QuestionListening {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "questionlisteningid", nullable = false)
  private int questionlisteningid;

  private String questionlisteningask;

  private String questionlisteninganswercorrect;

  private String questionlisteninganswer1;

  private String questionlisteninganswer2;

  private String questionlisteninganswer3;

  private String questionlisteninganswer4;

  private String questionlisteningexplain;

  @Transient
  @JsonIgnore
  private byte[] questionlisteningimagedata;

  private String questionlisteningimage;

  private String questionlisteningserial;

  private String questionlisteningaudio;

  @Column(columnDefinition = "TEXT")
  private String questionlisteningscript;

  @ManyToOne
  @JoinColumn(name = "listeningid")
  private Listening listening;

  public QuestionListening() {
  }

  @Override
  public String toString() {
    return "QuestionListening{" +
        "questionlisteningid=" + questionlisteningid +
        ", questionlisteningask='" + questionlisteningask + '\'' +
        ", questionlisteninganswercorrect='" + questionlisteninganswercorrect + '\'' +
        ", questionlisteninganswer1='" + questionlisteninganswer1 + '\'' +
        ", questionlisteninganswer2='" + questionlisteninganswer2 + '\'' +
        ", questionlisteninganswer3='" + questionlisteninganswer3 + '\'' +
        ", questionlisteninganswer4='" + questionlisteninganswer4 + '\'' +
        ", questionlisteningexplain='" + questionlisteningexplain + '\'' +
        ", questionlisteningimagedata=" + Arrays.toString(questionlisteningimagedata) +
        ", questionlisteningimage='" + questionlisteningimage + '\'' +
        ", questionlisteningserial='" + questionlisteningserial + '\'' +
        ", questionlisteningaudio='" + questionlisteningaudio + '\'' +
        ", questionlisteningscript='" + questionlisteningscript + '\'' +
        ", listening=" + listening +
        '}';
  }

  public int getQuestionlisteningid() {
    return questionlisteningid;
  }

  public void setQuestionlisteningid(int questionlisteningid) {
    this.questionlisteningid = questionlisteningid;
  }

  public String getQuestionlisteningask() {
    return questionlisteningask;
  }

  public void setQuestionlisteningask(String questionlisteningask) {
    this.questionlisteningask = questionlisteningask;
  }

  public String getQuestionlisteninganswercorrect() {
    return questionlisteninganswercorrect;
  }

  public void setQuestionlisteninganswercorrect(String questionlisteninganswercorrect) {
    this.questionlisteninganswercorrect = questionlisteninganswercorrect;
  }

  public String getQuestionlisteninganswer1() {
    return questionlisteninganswer1;
  }

  public void setQuestionlisteninganswer1(String questionlisteninganswer1) {
    this.questionlisteninganswer1 = questionlisteninganswer1;
  }

  public String getQuestionlisteninganswer2() {
    return questionlisteninganswer2;
  }

  public void setQuestionlisteninganswer2(String questionlisteninganswer2) {
    this.questionlisteninganswer2 = questionlisteninganswer2;
  }

  public String getQuestionlisteninganswer3() {
    return questionlisteninganswer3;
  }

  public void setQuestionlisteninganswer3(String questionlisteninganswer3) {
    this.questionlisteninganswer3 = questionlisteninganswer3;
  }

  public String getQuestionlisteninganswer4() {
    return questionlisteninganswer4;
  }

  public void setQuestionlisteninganswer4(String questionlisteninganswer4) {
    this.questionlisteninganswer4 = questionlisteninganswer4;
  }

  public String getQuestionlisteningexplain() {
    return questionlisteningexplain;
  }

  public void setQuestionlisteningexplain(String questionlisteningexplain) {
    this.questionlisteningexplain = questionlisteningexplain;
  }

  public byte[] getQuestionlisteningimagedata() {
    return questionlisteningimagedata;
  }

  public void setQuestionlisteningimagedata(byte[] questionlisteningimagedata) {
    this.questionlisteningimagedata = questionlisteningimagedata;
  }

  public String getQuestionlisteningimage() {
    return questionlisteningimage;
  }

  public void setQuestionlisteningimage(String questionlisteningimage) {
    this.questionlisteningimage = questionlisteningimage;
  }

  public String getQuestionlisteningserial() {
    return questionlisteningserial;
  }

  public void setQuestionlisteningserial(String questionlisteningserial) {
    this.questionlisteningserial = questionlisteningserial;
  }

  public String getQuestionlisteningaudio() {
    return questionlisteningaudio;
  }

  public void setQuestionlisteningaudio(String questionlisteningaudio) {
    this.questionlisteningaudio = questionlisteningaudio;
  }

  public String getQuestionlisteningscript() {
    return questionlisteningscript;
  }

  public void setQuestionlisteningscript(String questionlisteningscript) {
    this.questionlisteningscript = questionlisteningscript;
  }

  public Listening getListening() {
    return listening;
  }

  public void setListening(Listening listening) {
    this.listening = listening;
  }

  public QuestionListening(int questionlisteningid, String questionlisteningask,
      String questionlisteninganswercorrect, String questionlisteninganswer1,
      String questionlisteninganswer2, String questionlisteninganswer3,
      String questionlisteninganswer4, String questionlisteningexplain,
      byte[] questionlisteningimagedata, String questionlisteningimage,
      String questionlisteningserial, String questionlisteningaudio, String questionlisteningscript,
      Listening listening) {
    this.questionlisteningid = questionlisteningid;
    this.questionlisteningask = questionlisteningask;
    this.questionlisteninganswercorrect = questionlisteninganswercorrect;
    this.questionlisteninganswer1 = questionlisteninganswer1;
    this.questionlisteninganswer2 = questionlisteninganswer2;
    this.questionlisteninganswer3 = questionlisteninganswer3;
    this.questionlisteninganswer4 = questionlisteninganswer4;
    this.questionlisteningexplain = questionlisteningexplain;
    this.questionlisteningimagedata = questionlisteningimagedata;
    this.questionlisteningimage = questionlisteningimage;
    this.questionlisteningserial = questionlisteningserial;
    this.questionlisteningaudio = questionlisteningaudio;
    this.questionlisteningscript = questionlisteningscript;
    this.listening = listening;
  }
}
