package com.webtoiec.serverwebtoiec.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "questionreading")
public class QuestionReading {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int questionreadingid;

  private String questionreadingask;

  private String questionreadinganswercorrect;

  private String questionreadinganswer1;

  private String questionreadinganswer2;

  private String questionreadinganswer3;

  private String questionreadinganswer4;

  private String questionreadingexplain;

  private String questionreadingserial;

  @Column(columnDefinition = "TEXT")
  private String questionreadingscript;

  @ManyToOne
  @JoinColumn(name = "readingid")
  private Reading reading;

  public QuestionReading() {
  }

  @Override
  public String toString() {
    return "QuestionReading{" +
        "questionreadingid=" + questionreadingid +
        ", questionreadingask='" + questionreadingask + '\'' +
        ", questionreadinganswercorrect='" + questionreadinganswercorrect + '\'' +
        ", questionreadinganswer1='" + questionreadinganswer1 + '\'' +
        ", questionreadinganswer2='" + questionreadinganswer2 + '\'' +
        ", questionreadinganswer3='" + questionreadinganswer3 + '\'' +
        ", questionreadinganswer4='" + questionreadinganswer4 + '\'' +
        ", questionreadingexplain='" + questionreadingexplain + '\'' +
        ", questionreadingserial='" + questionreadingserial + '\'' +
        ", questionreadingscript='" + questionreadingscript + '\'' +
        ", reading=" + reading +
        '}';
  }

  public int getQuestionreadingid() {
    return questionreadingid;
  }

  public void setQuestionreadingid(int questionreadingid) {
    this.questionreadingid = questionreadingid;
  }

  public String getQuestionreadingask() {
    return questionreadingask;
  }

  public void setQuestionreadingask(String questionreadingask) {
    this.questionreadingask = questionreadingask;
  }

  public String getQuestionreadinganswercorrect() {
    return questionreadinganswercorrect;
  }

  public void setQuestionreadinganswercorrect(String questionreadinganswercorrect) {
    this.questionreadinganswercorrect = questionreadinganswercorrect;
  }

  public String getQuestionreadinganswer1() {
    return questionreadinganswer1;
  }

  public void setQuestionreadinganswer1(String questionreadinganswer1) {
    this.questionreadinganswer1 = questionreadinganswer1;
  }

  public String getQuestionreadinganswer2() {
    return questionreadinganswer2;
  }

  public void setQuestionreadinganswer2(String questionreadinganswer2) {
    this.questionreadinganswer2 = questionreadinganswer2;
  }

  public String getQuestionreadinganswer3() {
    return questionreadinganswer3;
  }

  public void setQuestionreadinganswer3(String questionreadinganswer3) {
    this.questionreadinganswer3 = questionreadinganswer3;
  }

  public String getQuestionreadinganswer4() {
    return questionreadinganswer4;
  }

  public void setQuestionreadinganswer4(String questionreadinganswer4) {
    this.questionreadinganswer4 = questionreadinganswer4;
  }

  public String getQuestionreadingexplain() {
    return questionreadingexplain;
  }

  public void setQuestionreadingexplain(String questionreadingexplain) {
    this.questionreadingexplain = questionreadingexplain;
  }

  public String getQuestionreadingserial() {
    return questionreadingserial;
  }

  public void setQuestionreadingserial(String questionreadingserial) {
    this.questionreadingserial = questionreadingserial;
  }

  public String getQuestionreadingscript() {
    return questionreadingscript;
  }

  public void setQuestionreadingscript(String questionreadingscript) {
    this.questionreadingscript = questionreadingscript;
  }

  public Reading getReading() {
    return reading;
  }

  public void setReading(Reading reading) {
    this.reading = reading;
  }

  public QuestionReading(int questionreadingid, String questionreadingask,
      String questionreadinganswercorrect, String questionreadinganswer1,
      String questionreadinganswer2, String questionreadinganswer3, String questionreadinganswer4,
      String questionreadingexplain, String questionreadingserial, String questionreadingscript,
      Reading reading) {
    this.questionreadingid = questionreadingid;
    this.questionreadingask = questionreadingask;
    this.questionreadinganswercorrect = questionreadinganswercorrect;
    this.questionreadinganswer1 = questionreadinganswer1;
    this.questionreadinganswer2 = questionreadinganswer2;
    this.questionreadinganswer3 = questionreadinganswer3;
    this.questionreadinganswer4 = questionreadinganswer4;
    this.questionreadingexplain = questionreadingexplain;
    this.questionreadingserial = questionreadingserial;
    this.questionreadingscript = questionreadingscript;
    this.reading = reading;
  }
}
