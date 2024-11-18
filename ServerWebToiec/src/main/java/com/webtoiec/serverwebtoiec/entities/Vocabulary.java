package com.webtoiec.serverwebtoiec.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vocabulary")
public class Vocabulary {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "vocabularyid", nullable = false)
  private int vocabularyid;

  @Column(name = "vocabularyname")
  private String vocabularyname;

  @Column(name = "vocabularyimage")
  private String vocabularyimage;

  public Vocabulary() {
  }

  @Override
  public String toString() {
    return "Vocabulary{" +
        "vocabularyid=" + vocabularyid +
        ", vocabularyname='" + vocabularyname + '\'' +
        ", vocabularyimage='" + vocabularyimage + '\'' +
        '}';
  }

  public int getVocabularyid() {
    return vocabularyid;
  }

  public void setVocabularyid(int vocabularyid) {
    this.vocabularyid = vocabularyid;
  }

  public String getVocabularyname() {
    return vocabularyname;
  }

  public void setVocabularyname(String vocabularyname) {
    this.vocabularyname = vocabularyname;
  }

  public String getVocabularyimage() {
    return vocabularyimage;
  }

  public void setVocabularyimage(String vocabularyimage) {
    this.vocabularyimage = vocabularyimage;
  }

  public Vocabulary(int vocabularyid, String vocabularyname, String vocabularyimage) {
    this.vocabularyid = vocabularyid;
    this.vocabularyname = vocabularyname;
    this.vocabularyimage = vocabularyimage;
  }
}
