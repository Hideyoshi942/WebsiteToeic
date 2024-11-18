package com.webtoiec.serverwebtoiec.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "commentgrammar")
public class CommentGrammar {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int commentgrammarid;

  @Column(columnDefinition = "TEXT")
  private String commentgrammarcontent;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
  private Date commentgrammartime;

  @ManyToOne
  @JoinColumn(name = "grammarid", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Grammar grammar;

  @ManyToOne
  @JoinColumn(name = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private UserToeic user;

  public CommentGrammar() {
  }

  public CommentGrammar(int commentgrammarid, String commentgrammarcontent,
      Date commentgrammartime,
      Grammar grammar, UserToeic user) {
    this.commentgrammarid = commentgrammarid;
    this.commentgrammarcontent = commentgrammarcontent;
    this.commentgrammartime = commentgrammartime;
    this.grammar = grammar;
    this.user = user;
  }

  public int getCommentgrammarid() {
    return commentgrammarid;
  }

  public void setCommentgrammarid(int commentgrammarid) {
    this.commentgrammarid = commentgrammarid;
  }

  public String getCommentgrammarcontent() {
    return commentgrammarcontent;
  }

  public void setCommentgrammarcontent(String commentgrammarcontent) {
    this.commentgrammarcontent = commentgrammarcontent;
  }

  public Date getCommentgrammartime() {
    return commentgrammartime;
  }

  public void setCommentgrammartime(Date commentgrammartime) {
    this.commentgrammartime = commentgrammartime;
  }

  public Grammar getGrammar() {
    return grammar;
  }

  public void setGrammar(Grammar grammar) {
    this.grammar = grammar;
  }

  public UserToeic getUser() {
    return user;
  }

  public void setUser(UserToeic user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "CommentGrammar{" +
        "commentgrammarid=" + commentgrammarid +
        ", commentgrammarcontent='" + commentgrammarcontent + '\'' +
        ", commentgrammartime=" + commentgrammartime +
        ", grammar=" + grammar +
        ", user=" + user +
        '}';
  }
}
