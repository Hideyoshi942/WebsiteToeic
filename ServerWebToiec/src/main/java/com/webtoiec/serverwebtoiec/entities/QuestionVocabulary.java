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
@Table(name = "questionvocabulary")
public class QuestionVocabulary {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int questionvocabularyid;

  private String questionvocabularyask;

  private String questionvocabularyanswercorrect;

  private String questionvocabularyanswer1;

  private String questionvocabularyanswer2;

  private String questionvocabularyanswer3;

  private String questionvocabularyanswer4;

  private String questionvocabularyexplain;

  private String questionvocabularyserial;

  @ManyToOne
  @JoinColumn(name = "vocabularyid")
  private Vocabulary vocabulary;


  public QuestionVocabulary() {
  }

  @Override
  public String toString() {
    return "QuestionVocabulary{" +
        "questionvocabularyid=" + questionvocabularyid +
        ", questionvocabularyask='" + questionvocabularyask + '\'' +
        ", questionvocabularyanswercorrect='" + questionvocabularyanswercorrect + '\'' +
        ", questionvocabularyanswer1='" + questionvocabularyanswer1 + '\'' +
        ", questionvocabularyanswer2='" + questionvocabularyanswer2 + '\'' +
        ", questionvocabularyanswer3='" + questionvocabularyanswer3 + '\'' +
        ", questionvocabularyanswer4='" + questionvocabularyanswer4 + '\'' +
        ", questionvocabularyexplain='" + questionvocabularyexplain + '\'' +
        ", questionvocabularyserial='" + questionvocabularyserial + '\'' +
        ", vocabulary=" + vocabulary +
        '}';
  }

  public int getQuestionvocabularyid() {
    return questionvocabularyid;
  }

  public void setQuestionvocabularyid(int questionvocabularyid) {
    this.questionvocabularyid = questionvocabularyid;
  }

  public String getQuestionvocabularyask() {
    return questionvocabularyask;
  }

  public void setQuestionvocabularyask(String questionvocabularyask) {
    this.questionvocabularyask = questionvocabularyask;
  }

  public String getQuestionvocabularyanswercorrect() {
    return questionvocabularyanswercorrect;
  }

  public void setQuestionvocabularyanswercorrect(String questionvocabularyanswercorrect) {
    this.questionvocabularyanswercorrect = questionvocabularyanswercorrect;
  }

  public String getQuestionvocabularyanswer1() {
    return questionvocabularyanswer1;
  }

  public void setQuestionvocabularyanswer1(String questionvocabularyanswer1) {
    this.questionvocabularyanswer1 = questionvocabularyanswer1;
  }

  public String getQuestionvocabularyanswer2() {
    return questionvocabularyanswer2;
  }

  public void setQuestionvocabularyanswer2(String questionvocabularyanswer2) {
    this.questionvocabularyanswer2 = questionvocabularyanswer2;
  }

  public String getQuestionvocabularyanswer3() {
    return questionvocabularyanswer3;
  }

  public void setQuestionvocabularyanswer3(String questionvocabularyanswer3) {
    this.questionvocabularyanswer3 = questionvocabularyanswer3;
  }

  public String getQuestionvocabularyanswer4() {
    return questionvocabularyanswer4;
  }

  public void setQuestionvocabularyanswer4(String questionvocabularyanswer4) {
    this.questionvocabularyanswer4 = questionvocabularyanswer4;
  }

  public String getQuestionvocabularyexplain() {
    return questionvocabularyexplain;
  }

  public void setQuestionvocabularyexplain(String questionvocabularyexplain) {
    this.questionvocabularyexplain = questionvocabularyexplain;
  }

  public String getQuestionvocabularyserial() {
    return questionvocabularyserial;
  }

  public void setQuestionvocabularyserial(String questionvocabularyserial) {
    this.questionvocabularyserial = questionvocabularyserial;
  }

  public Vocabulary getVocabulary() {
    return vocabulary;
  }

  public void setVocabulary(Vocabulary vocabulary) {
    this.vocabulary = vocabulary;
  }

  public QuestionVocabulary(int questionvocabularyid, String questionvocabularyask,
      String questionvocabularyanswercorrect, String questionvocabularyanswer1,
      String questionvocabularyanswer2, String questionvocabularyanswer3,
      String questionvocabularyanswer4, String questionvocabularyexplain,
      String questionvocabularyserial, Vocabulary vocabulary) {
    this.questionvocabularyid = questionvocabularyid;
    this.questionvocabularyask = questionvocabularyask;
    this.questionvocabularyanswercorrect = questionvocabularyanswercorrect;
    this.questionvocabularyanswer1 = questionvocabularyanswer1;
    this.questionvocabularyanswer2 = questionvocabularyanswer2;
    this.questionvocabularyanswer3 = questionvocabularyanswer3;
    this.questionvocabularyanswer4 = questionvocabularyanswer4;
    this.questionvocabularyexplain = questionvocabularyexplain;
    this.questionvocabularyserial = questionvocabularyserial;
    this.vocabulary = vocabulary;
  }
}
