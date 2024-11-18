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
@Table(name = "grammarlessoncontent")
public class GrammarLessonContent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int grammarlessoncontentid;

  private String grammarlessoncontentaudio;

  private String grammarlessoncontentdescript;

  private String grammarlessoncontentimage;

  private String grammarlessoncontentmeaning;

  private String grammarlessoncontentnumber;

  private String grammarlessoncontentsentence;

  private String grammarlessoncontenttranscribe;

  @ManyToOne
  @JoinColumn(name = "grammarid", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Grammar grammar;

  public GrammarLessonContent() {
  }

  @Override
  public String toString() {
    return "GrammarLessonContent{" +
        "grammarlessoncontentid=" + grammarlessoncontentid +
        ", grammarlessoncontentaudio='" + grammarlessoncontentaudio + '\'' +
        ", grammarlessoncontentdescript='" + grammarlessoncontentdescript + '\'' +
        ", grammarlessoncontentimage='" + grammarlessoncontentimage + '\'' +
        ", grammarlessoncontentmeaning='" + grammarlessoncontentmeaning + '\'' +
        ", grammarlessoncontentnumber='" + grammarlessoncontentnumber + '\'' +
        ", grammarlessoncontentsentence='" + grammarlessoncontentsentence + '\'' +
        ", grammarlessoncontenttranscribe='" + grammarlessoncontenttranscribe + '\'' +
        ", grammar=" + grammar +
        '}';
  }

  public int getGrammarlessoncontentid() {
    return grammarlessoncontentid;
  }

  public void setGrammarlessoncontentid(int grammarlessoncontentid) {
    this.grammarlessoncontentid = grammarlessoncontentid;
  }

  public String getGrammarlessoncontentaudio() {
    return grammarlessoncontentaudio;
  }

  public void setGrammarlessoncontentaudio(String grammarlessoncontentaudio) {
    this.grammarlessoncontentaudio = grammarlessoncontentaudio;
  }

  public String getGrammarlessoncontentdescript() {
    return grammarlessoncontentdescript;
  }

  public void setGrammarlessoncontentdescript(String grammarlessoncontentdescript) {
    this.grammarlessoncontentdescript = grammarlessoncontentdescript;
  }

  public String getGrammarlessoncontentimage() {
    return grammarlessoncontentimage;
  }

  public void setGrammarlessoncontentimage(String grammarlessoncontentimage) {
    this.grammarlessoncontentimage = grammarlessoncontentimage;
  }

  public String getGrammarlessoncontentmeaning() {
    return grammarlessoncontentmeaning;
  }

  public void setGrammarlessoncontentmeaning(String grammarlessoncontentmeaning) {
    this.grammarlessoncontentmeaning = grammarlessoncontentmeaning;
  }

  public String getGrammarlessoncontentnumber() {
    return grammarlessoncontentnumber;
  }

  public void setGrammarlessoncontentnumber(String grammarlessoncontentnumber) {
    this.grammarlessoncontentnumber = grammarlessoncontentnumber;
  }

  public String getGrammarlessoncontentsentence() {
    return grammarlessoncontentsentence;
  }

  public void setGrammarlessoncontentsentence(String grammarlessoncontentsentence) {
    this.grammarlessoncontentsentence = grammarlessoncontentsentence;
  }

  public String getGrammarlessoncontenttranscribe() {
    return grammarlessoncontenttranscribe;
  }

  public void setGrammarlessoncontenttranscribe(String grammarlessoncontenttranscribe) {
    this.grammarlessoncontenttranscribe = grammarlessoncontenttranscribe;
  }

  public Grammar getGrammar() {
    return grammar;
  }

  public void setGrammar(Grammar grammar) {
    this.grammar = grammar;
  }

  public GrammarLessonContent(int grammarlessoncontentid, String grammarlessoncontentaudio,
      String grammarlessoncontentdescript, String grammarlessoncontentimage,
      String grammarlessoncontentmeaning, String grammarlessoncontentnumber,
      String grammarlessoncontentsentence, String grammarlessoncontenttranscribe, Grammar grammar) {
    this.grammarlessoncontentid = grammarlessoncontentid;
    this.grammarlessoncontentaudio = grammarlessoncontentaudio;
    this.grammarlessoncontentdescript = grammarlessoncontentdescript;
    this.grammarlessoncontentimage = grammarlessoncontentimage;
    this.grammarlessoncontentmeaning = grammarlessoncontentmeaning;
    this.grammarlessoncontentnumber = grammarlessoncontentnumber;
    this.grammarlessoncontentsentence = grammarlessoncontentsentence;
    this.grammarlessoncontenttranscribe = grammarlessoncontenttranscribe;
    this.grammar = grammar;
  }
}
