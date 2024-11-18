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
@Table(name = "commentreading")
public class CommentReading {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int commentreadingid;

  @Column(columnDefinition = "TEXT")
  private String commentreadingcontent;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
  private Date commentreadingtime;

  @ManyToOne
  @JoinColumn(name = "readingid", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Reading reading;

  @ManyToOne
  @JoinColumn(name = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private UserToeic user;

  public CommentReading() {
  }

  @Override
  public String toString() {
    return "CommentReading{" +
        "commentreadingid=" + commentreadingid +
        ", commentreadingcontent='" + commentreadingcontent + '\'' +
        ", commentreadingtime=" + commentreadingtime +
        ", reading=" + reading +
        ", user=" + user +
        '}';
  }

  public int getCommentreadingid() {
    return commentreadingid;
  }

  public void setCommentreadingid(int commentreadingid) {
    this.commentreadingid = commentreadingid;
  }

  public String getCommentreadingcontent() {
    return commentreadingcontent;
  }

  public void setCommentreadingcontent(String commentreadingcontent) {
    this.commentreadingcontent = commentreadingcontent;
  }

  public Date getCommentreadingtime() {
    return commentreadingtime;
  }

  public void setCommentreadingtime(Date commentreadingtime) {
    this.commentreadingtime = commentreadingtime;
  }

  public Reading getReading() {
    return reading;
  }

  public void setReading(Reading reading) {
    this.reading = reading;
  }

  public UserToeic getUser() {
    return user;
  }

  public void setUser(UserToeic user) {
    this.user = user;
  }

  public CommentReading(int commentreadingid, String commentreadingcontent,
      Date commentreadingtime, Reading reading, UserToeic user) {
    this.commentreadingid = commentreadingid;
    this.commentreadingcontent = commentreadingcontent;
    this.commentreadingtime = commentreadingtime;
    this.reading = reading;
    this.user = user;
  }
}
