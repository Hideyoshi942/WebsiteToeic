package com.webtoiec.serverwebtoiec.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "questiongrammar")
public class QuestionGrammar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int questiongrammarid;

  private String questiongrammarask;

  private String questiongrammaranswercorrect;

  private String questiongrammaranswer1;

  private String questiongrammaranswer2;

  private String questiongrammaranswer3;

  private String questiongrammaranswer4;

  private String questiongrammarexplain;

  private String questiongrammarserial;

  @ManyToOne
  @JoinColumn(name = "grammarid")
  private Grammar grammar;

  public QuestionGrammar() {
  }

  @Override
  public String toString() {
    return "QuestionGrammar{" +
        "questiongrammarid=" + questiongrammarid +
        ", questiongrammarask='" + questiongrammarask + '\'' +
        ", questiongrammaranswercorrect='" + questiongrammaranswercorrect + '\'' +
        ", questiongrammaranswer1='" + questiongrammaranswer1 + '\'' +
        ", questiongrammaranswer2='" + questiongrammaranswer2 + '\'' +
        ", questiongrammaranswer3='" + questiongrammaranswer3 + '\'' +
        ", questiongrammaranswer4='" + questiongrammaranswer4 + '\'' +
        ", questiongrammarexplain='" + questiongrammarexplain + '\'' +
        ", questiongrammarserial='" + questiongrammarserial + '\'' +
        ", grammar=" + grammar +
        '}';
  }

  public int getQuestiongrammarid() {
    return questiongrammarid;
  }

  public void setQuestiongrammarid(int questiongrammarid) {
    this.questiongrammarid = questiongrammarid;
  }

  public String getQuestiongrammarask() {
    return questiongrammarask;
  }

  public void setQuestiongrammarask(String questiongrammarask) {
    this.questiongrammarask = questiongrammarask;
  }

  public String getQuestiongrammaranswercorrect() {
    return questiongrammaranswercorrect;
  }

  public void setQuestiongrammaranswercorrect(String questiongrammaranswercorrect) {
    this.questiongrammaranswercorrect = questiongrammaranswercorrect;
  }

  public String getQuestiongrammaranswer1() {
    return questiongrammaranswer1;
  }

  public void setQuestiongrammaranswer1(String questiongrammaranswer1) {
    this.questiongrammaranswer1 = questiongrammaranswer1;
  }

  public String getQuestiongrammaranswer2() {
    return questiongrammaranswer2;
  }

  public void setQuestiongrammaranswer2(String questiongrammaranswer2) {
    this.questiongrammaranswer2 = questiongrammaranswer2;
  }

  public String getQuestiongrammaranswer3() {
    return questiongrammaranswer3;
  }

  public void setQuestiongrammaranswer3(String questiongrammaranswer3) {
    this.questiongrammaranswer3 = questiongrammaranswer3;
  }

  public String getQuestiongrammaranswer4() {
    return questiongrammaranswer4;
  }

  public void setQuestiongrammaranswer4(String questiongrammaranswer4) {
    this.questiongrammaranswer4 = questiongrammaranswer4;
  }

  public String getQuestiongrammarexplain() {
    return questiongrammarexplain;
  }

  public void setQuestiongrammarexplain(String questiongrammarexplain) {
    this.questiongrammarexplain = questiongrammarexplain;
  }

  public String getQuestiongrammarserial() {
    return questiongrammarserial;
  }

  public void setQuestiongrammarserial(String questiongrammarserial) {
    this.questiongrammarserial = questiongrammarserial;
  }

  public Grammar getGrammar() {
    return grammar;
  }

  public void setGrammar(Grammar grammar) {
    this.grammar = grammar;
  }

  public QuestionGrammar(int questiongrammarid, String questiongrammarask,
      String questiongrammaranswercorrect, String questiongrammaranswer1,
      String questiongrammaranswer2, String questiongrammaranswer3, String questiongrammaranswer4,
      String questiongrammarexplain, String questiongrammarserial, Grammar grammar) {
    this.questiongrammarid = questiongrammarid;
    this.questiongrammarask = questiongrammarask;
    this.questiongrammaranswercorrect = questiongrammaranswercorrect;
    this.questiongrammaranswer1 = questiongrammaranswer1;
    this.questiongrammaranswer2 = questiongrammaranswer2;
    this.questiongrammaranswer3 = questiongrammaranswer3;
    this.questiongrammaranswer4 = questiongrammaranswer4;
    this.questiongrammarexplain = questiongrammarexplain;
    this.questiongrammarserial = questiongrammarserial;
    this.grammar = grammar;
  }
}
