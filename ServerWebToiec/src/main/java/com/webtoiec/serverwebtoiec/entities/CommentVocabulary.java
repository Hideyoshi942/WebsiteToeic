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
@Table(name = "commentvocabulary")
public class CommentVocabulary {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int commentvocabularyid;

  @Column(columnDefinition = "TEXT")
  private String commentvocabularycontent;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
  private Date commentvocabularytime;

  @ManyToOne
  @JoinColumn(name = "vocabularyid", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Vocabulary vocabulary;

  @ManyToOne
  @JoinColumn(name = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private UserToeic user;

  public CommentVocabulary() {
  }

  @Override
  public String toString() {
    return "CommentVocabulary{" +
        "commentvocabularyid=" + commentvocabularyid +
        ", commentvocabularycontent='" + commentvocabularycontent + '\'' +
        ", commentvocabularytime=" + commentvocabularytime +
        ", vocabulary=" + vocabulary +
        ", user=" + user +
        '}';
  }

  public int getCommentvocabularyid() {
    return commentvocabularyid;
  }

  public void setCommentvocabularyid(int commentvocabularyid) {
    this.commentvocabularyid = commentvocabularyid;
  }

  public String getCommentvocabularycontent() {
    return commentvocabularycontent;
  }

  public void setCommentvocabularycontent(String commentvocabularycontent) {
    this.commentvocabularycontent = commentvocabularycontent;
  }

  public Date getCommentvocabularytime() {
    return commentvocabularytime;
  }

  public void setCommentvocabularytime(Date commentvocabularytime) {
    this.commentvocabularytime = commentvocabularytime;
  }

  public Vocabulary getVocabulary() {
    return vocabulary;
  }

  public void setVocabulary(Vocabulary vocabulary) {
    this.vocabulary = vocabulary;
  }

  public UserToeic getUser() {
    return user;
  }

  public void setUser(UserToeic user) {
    this.user = user;
  }

  public CommentVocabulary(int commentvocabularyid, String commentvocabularycontent,
      Date commentvocabularytime, Vocabulary vocabulary, UserToeic user) {
    this.commentvocabularyid = commentvocabularyid;
    this.commentvocabularycontent = commentvocabularycontent;
    this.commentvocabularytime = commentvocabularytime;
    this.vocabulary = vocabulary;
    this.user = user;
  }
}
