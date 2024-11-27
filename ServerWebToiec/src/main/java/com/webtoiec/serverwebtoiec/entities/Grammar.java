package com.webtoiec.serverwebtoiec.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "grammar")
public class Grammar {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "grammarid", nullable = false)
  private int grammarid;

  @Column(name = "grammarname")
  private String grammarname;

  @Column(name = "grammarimage")
  private String grammarimage;

  private String grammarcontenthtml;

  @Column(columnDefinition = "nvarchar(max)")
  private String grammarcontentmarkdown;

  public Grammar() {
  }

  @Override
  public String toString() {
    return "Grammar{" +
        "grammarid=" + grammarid +
        ", grammarname='" + grammarname + '\'' +
        ", grammarimage='" + grammarimage + '\'' +
        ", grammarcontenthtml='" + grammarcontenthtml + '\'' +
        ", grammarcontentmarkdown='" + grammarcontentmarkdown + '\'' +
        '}';
  }

  public int getGrammarid() {
    return grammarid;
  }

  public void setGrammarid(int grammarid) {
    this.grammarid = grammarid;
  }

  public String getGrammarname() {
    return grammarname;
  }

  public void setGrammarname(String grammarname) {
    this.grammarname = grammarname;
  }

  public String getGrammarimage() {
    return grammarimage;
  }

  public void setGrammarimage(String grammarimage) {
    this.grammarimage = grammarimage;
  }

  public String getGrammarcontenthtml() {
    return grammarcontenthtml;
  }

  public void setGrammarcontenthtml(String grammarcontenthtml) {
    this.grammarcontenthtml = grammarcontenthtml;
  }

  public String getGrammarcontentmarkdown() {
    return grammarcontentmarkdown;
  }

  public void setGrammarcontentmarkdown(String grammarcontentmarkdown) {
    this.grammarcontentmarkdown = grammarcontentmarkdown;
  }

  public Grammar(int grammarid, String grammarname, String grammarimage, String grammarcontenthtml,
      String grammarcontentmarkdown) {
    this.grammarid = grammarid;
    this.grammarname = grammarname;
    this.grammarimage = grammarimage;
    this.grammarcontenthtml = grammarcontenthtml;
    this.grammarcontentmarkdown = grammarcontentmarkdown;
  }
}
