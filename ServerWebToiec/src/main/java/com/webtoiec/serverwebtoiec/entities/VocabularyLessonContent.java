package com.webtoiec.serverwebtoiec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "vocabularylessoncontent")
public class VocabularyLessonContent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int vocabularylessoncontentid;

  private String vocabularylessoncontentaudio;
  private String vocabularylessoncontentdescript;
  private String vocabularylessoncontentimage;
  private String vocabularylessoncontentmeaning;
  private String vocabularylessoncontentnumber;
  private String vocabularylessoncontentsentence;
  private String vocabularylessoncontenttranscript;

  @ManyToOne
  @JoinColumn(name = "vocabularyid", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Vocabulary vocabulary;

  public VocabularyLessonContent() {
  }

  @Override
  public String toString() {
    return "VocabularyLessonContent{" +
        "vocabularylessoncontentid=" + vocabularylessoncontentid +
        ", vocabularylessoncontentaudio='" + vocabularylessoncontentaudio + '\'' +
        ", vocabularylessoncontentdescript='" + vocabularylessoncontentdescript + '\'' +
        ", vocabularylessoncontentimage='" + vocabularylessoncontentimage + '\'' +
        ", vocabularylessoncontentmeaning='" + vocabularylessoncontentmeaning + '\'' +
        ", vocabularylessoncontentnumber='" + vocabularylessoncontentnumber + '\'' +
        ", vocabularylessoncontentsentence='" + vocabularylessoncontentsentence + '\'' +
        ", vocabularylessoncontenttranscript='" + vocabularylessoncontenttranscript + '\'' +
        ", vocabulary=" + vocabulary +
        '}';
  }

  public int getVocabularylessoncontentid() {
    return vocabularylessoncontentid;
  }

  public void setVocabularylessoncontentid(int vocabularylessoncontentid) {
    this.vocabularylessoncontentid = vocabularylessoncontentid;
  }

  public String getVocabularylessoncontentaudio() {
    return vocabularylessoncontentaudio;
  }

  public void setVocabularylessoncontentaudio(String vocabularylessoncontentaudio) {
    this.vocabularylessoncontentaudio = vocabularylessoncontentaudio;
  }

  public String getVocabularylessoncontentdescript() {
    return vocabularylessoncontentdescript;
  }

  public void setVocabularylessoncontentdescript(String vocabularylessoncontentdescript) {
    this.vocabularylessoncontentdescript = vocabularylessoncontentdescript;
  }

  public String getVocabularylessoncontentimage() {
    return vocabularylessoncontentimage;
  }

  public void setVocabularylessoncontentimage(String vocabularylessoncontentimage) {
    this.vocabularylessoncontentimage = vocabularylessoncontentimage;
  }

  public String getVocabularylessoncontentmeaning() {
    return vocabularylessoncontentmeaning;
  }

  public void setVocabularylessoncontentmeaning(String vocabularylessoncontentmeaning) {
    this.vocabularylessoncontentmeaning = vocabularylessoncontentmeaning;
  }

  public String getVocabularylessoncontentnumber() {
    return vocabularylessoncontentnumber;
  }

  public void setVocabularylessoncontentnumber(String vocabularylessoncontentnumber) {
    this.vocabularylessoncontentnumber = vocabularylessoncontentnumber;
  }

  public String getVocabularylessoncontentsentence() {
    return vocabularylessoncontentsentence;
  }

  public void setVocabularylessoncontentsentence(String vocabularylessoncontentsentence) {
    this.vocabularylessoncontentsentence = vocabularylessoncontentsentence;
  }

  public String getVocabularylessoncontenttranscript() {
    return vocabularylessoncontenttranscript;
  }

  public void setVocabularylessoncontenttranscript(String vocabularylessoncontenttranscript) {
    this.vocabularylessoncontenttranscript = vocabularylessoncontenttranscript;
  }

  public Vocabulary getVocabulary() {
    return vocabulary;
  }

  public void setVocabulary(Vocabulary vocabulary) {
    this.vocabulary = vocabulary;
  }

  public VocabularyLessonContent(int vocabularylessoncontentid,
      String vocabularylessoncontentaudio,
      String vocabularylessoncontentdescript, String vocabularylessoncontentimage,
      String vocabularylessoncontentmeaning, String vocabularylessoncontentnumber,
      String vocabularylessoncontentsentence, String vocabularylessoncontenttranscript,
      Vocabulary vocabulary) {
    this.vocabularylessoncontentid = vocabularylessoncontentid;
    this.vocabularylessoncontentaudio = vocabularylessoncontentaudio;
    this.vocabularylessoncontentdescript = vocabularylessoncontentdescript;
    this.vocabularylessoncontentimage = vocabularylessoncontentimage;
    this.vocabularylessoncontentmeaning = vocabularylessoncontentmeaning;
    this.vocabularylessoncontentnumber = vocabularylessoncontentnumber;
    this.vocabularylessoncontentsentence = vocabularylessoncontentsentence;
    this.vocabularylessoncontenttranscript = vocabularylessoncontenttranscript;
    this.vocabulary = vocabulary;
  }
}
