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
@Table(name = "commentlistening")
public class CommentListening {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int commentlisteningid;

  @Column(columnDefinition = "TEXT")
  private String commentlisteningcontent;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
  private Date commentlisteningtime;

  @ManyToOne
  @JoinColumn(name = "listeningid", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Listening listening;

  @ManyToOne
  @JoinColumn(name = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private UserToeic id;

  public CommentListening() {
  }

  @Override
  public String toString() {
    return "CommentListening{" +
        "commentlisteningid=" + commentlisteningid +
        ", commentlisteningcontent='" + commentlisteningcontent + '\'' +
        ", commentlisteningtime=" + commentlisteningtime +
        ", listening=" + listening +
        ", id=" + id +
        '}';
  }

  public int getCommentlisteningid() {
    return commentlisteningid;
  }

  public void setCommentlisteningid(int commentlisteningid) {
    this.commentlisteningid = commentlisteningid;
  }

  public String getCommentlisteningcontent() {
    return commentlisteningcontent;
  }

  public void setCommentlisteningcontent(String commentlisteningcontent) {
    this.commentlisteningcontent = commentlisteningcontent;
  }

  public Date getCommentlisteningtime() {
    return commentlisteningtime;
  }

  public void setCommentlisteningtime(Date commentlisteningtime) {
    this.commentlisteningtime = commentlisteningtime;
  }

  public Listening getListening() {
    return listening;
  }

  public void setListening(Listening listening) {
    this.listening = listening;
  }

  public UserToeic getId() {
    return id;
  }

  public void setId(UserToeic id) {
    this.id = id;
  }

  public CommentListening(int commentlisteningid, String commentlisteningcontent,
      Date commentlisteningtime, Listening listening, UserToeic id) {
    this.commentlisteningid = commentlisteningid;
    this.commentlisteningcontent = commentlisteningcontent;
    this.commentlisteningtime = commentlisteningtime;
    this.listening = listening;
    this.id = id;
  }
}
