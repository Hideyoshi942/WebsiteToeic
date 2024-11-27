package com.webtoiec.serverwebtoiec.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "questionpracticetest")
public class QuestionPracticeTest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int questionpracticetestid;

  private String questionpracticetestaudio;

  private String questionpracticetestanswercorrect;

  private String questionpracticetestansweruser;

  private String questionpracticetestimage;

  private int questionpracticetestnumber;

  private String questionpracticetestanswer1;

  private String questionpracticetestanswer2;

  private String questionpracticetestanswer3;

  private String questionpracticetestanswer4;

  @Column(columnDefinition = "TEXT")
  private String questionpracticetestparagraph;

  private String questionpracticetestask;

  @ManyToOne
  @JoinColumn(name = "practicetestid", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private PracticeTest practicetest;

  public QuestionPracticeTest() {
  }

  @Override
  public String toString() {
    return "QuestionPracticeTest{" +
        "questionpracticetestid=" + questionpracticetestid +
        ", questionpracticetestaudio='" + questionpracticetestaudio + '\'' +
        ", questionpracticetestanswercorrect='" + questionpracticetestanswercorrect + '\'' +
        ", questionpracticetestansweruser='" + questionpracticetestansweruser + '\'' +
        ", questionpracticetestimage='" + questionpracticetestimage + '\'' +
        ", questionpracticetestnumber=" + questionpracticetestnumber +
        ", questionpracticetestanswer1='" + questionpracticetestanswer1 + '\'' +
        ", questionpracticetestanswer2='" + questionpracticetestanswer2 + '\'' +
        ", questionpracticetestanswer3='" + questionpracticetestanswer3 + '\'' +
        ", questionpracticetestanswer4='" + questionpracticetestanswer4 + '\'' +
        ", questionpracticetestparagraph='" + questionpracticetestparagraph + '\'' +
        ", questionpracticetestask='" + questionpracticetestask + '\'' +
        ", practicetest=" + practicetest +
        '}';
  }

  public int getQuestionpracticetestid() {
    return questionpracticetestid;
  }

  public void setQuestionpracticetestid(int questionpracticetestid) {
    this.questionpracticetestid = questionpracticetestid;
  }

  public String getQuestionpracticetestaudio() {
    return questionpracticetestaudio;
  }

  public void setQuestionpracticetestaudio(String questionpracticetestaudio) {
    this.questionpracticetestaudio = questionpracticetestaudio;
  }

  public String getQuestionpracticetestanswercorrect() {
    return questionpracticetestanswercorrect;
  }

  public void setQuestionpracticetestanswercorrect(String questionpracticetestanswercorrect) {
    this.questionpracticetestanswercorrect = questionpracticetestanswercorrect;
  }

  public String getQuestionpracticetestansweruser() {
    return questionpracticetestansweruser;
  }

  public void setQuestionpracticetestansweruser(String questionpracticetestansweruser) {
    this.questionpracticetestansweruser = questionpracticetestansweruser;
  }

  public String getQuestionpracticetestimage() {
    return questionpracticetestimage;
  }

  public void setQuestionpracticetestimage(String questionpracticetestimage) {
    this.questionpracticetestimage = questionpracticetestimage;
  }

  public int getQuestionpracticetestnumber() {
    return questionpracticetestnumber;
  }

  public void setQuestionpracticetestnumber(int questionpracticetestnumber) {
    this.questionpracticetestnumber = questionpracticetestnumber;
  }

  public String getQuestionpracticetestanswer1() {
    return questionpracticetestanswer1;
  }

  public void setQuestionpracticetestanswer1(String questionpracticetestanswer1) {
    this.questionpracticetestanswer1 = questionpracticetestanswer1;
  }

  public String getQuestionpracticetestanswer2() {
    return questionpracticetestanswer2;
  }

  public void setQuestionpracticetestanswer2(String questionpracticetestanswer2) {
    this.questionpracticetestanswer2 = questionpracticetestanswer2;
  }

  public String getQuestionpracticetestanswer3() {
    return questionpracticetestanswer3;
  }

  public void setQuestionpracticetestanswer3(String questionpracticetestanswer3) {
    this.questionpracticetestanswer3 = questionpracticetestanswer3;
  }

  public String getQuestionpracticetestanswer4() {
    return questionpracticetestanswer4;
  }

  public void setQuestionpracticetestanswer4(String questionpracticetestanswer4) {
    this.questionpracticetestanswer4 = questionpracticetestanswer4;
  }

  public String getQuestionpracticetestparagraph() {
    return questionpracticetestparagraph;
  }

  public void setQuestionpracticetestparagraph(String questionpracticetestparagraph) {
    this.questionpracticetestparagraph = questionpracticetestparagraph;
  }

  public String getQuestionpracticetestask() {
    return questionpracticetestask;
  }

  public void setQuestionpracticetestask(String questionpracticetestask) {
    this.questionpracticetestask = questionpracticetestask;
  }

  public PracticeTest getPracticetest() {
    return practicetest;
  }

  public void setPracticetest(PracticeTest practicetest) {
    this.practicetest = practicetest;
  }

  public QuestionPracticeTest(int questionpracticetestid, String questionpracticetestaudio,
      String questionpracticetestanswercorrect, String questionpracticetestansweruser,
      String questionpracticetestimage, int questionpracticetestnumber,
      String questionpracticetestanswer1, String questionpracticetestanswer2,
      String questionpracticetestanswer3, String questionpracticetestanswer4,
      String questionpracticetestparagraph, String questionpracticetestask,
      PracticeTest practicetest) {
    this.questionpracticetestid = questionpracticetestid;
    this.questionpracticetestaudio = questionpracticetestaudio;
    this.questionpracticetestanswercorrect = questionpracticetestanswercorrect;
    this.questionpracticetestansweruser = questionpracticetestansweruser;
    this.questionpracticetestimage = questionpracticetestimage;
    this.questionpracticetestnumber = questionpracticetestnumber;
    this.questionpracticetestanswer1 = questionpracticetestanswer1;
    this.questionpracticetestanswer2 = questionpracticetestanswer2;
    this.questionpracticetestanswer3 = questionpracticetestanswer3;
    this.questionpracticetestanswer4 = questionpracticetestanswer4;
    this.questionpracticetestparagraph = questionpracticetestparagraph;
    this.questionpracticetestask = questionpracticetestask;
    this.practicetest = practicetest;
  }
}
