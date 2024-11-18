package com.webtoiec.serverwebtoiec.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "resulttest")
public class ResultTest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int resulttestid;

  private int resulttestcorrectlistening;

  private int resulttestcorrectreading;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
  private Date resulttesttime;

  private int resulttestnumbercorrect;

  private int resulttestnumberincorrect;

  @ManyToOne
  @JoinColumn(name = "practicetestid", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private PracticeTest practicetest;

  @ManyToOne
  @JoinColumn(name = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private UserToeic user;

  public ResultTest() {

  }

  @Override
  public String toString() {
    return "ResultTest{" +
        "resulttestid=" + resulttestid +
        ", resulttestcorrectlistening=" + resulttestcorrectlistening +
        ", resulttestcorrectreading=" + resulttestcorrectreading +
        ", resulttesttime=" + resulttesttime +
        ", resulttestnumbercorrect=" + resulttestnumbercorrect +
        ", resulttestnumberincorrect=" + resulttestnumberincorrect +
        ", practicetest=" + practicetest +
        ", user=" + user +
        '}';
  }

  public int getResulttestid() {
    return resulttestid;
  }

  public void setResulttestid(int resulttestid) {
    this.resulttestid = resulttestid;
  }

  public int getResulttestcorrectlistening() {
    return resulttestcorrectlistening;
  }

  public void setResulttestcorrectlistening(int resulttestcorrectlistening) {
    this.resulttestcorrectlistening = resulttestcorrectlistening;
  }

  public int getResulttestcorrectreading() {
    return resulttestcorrectreading;
  }

  public void setResulttestcorrectreading(int resulttestcorrectreading) {
    this.resulttestcorrectreading = resulttestcorrectreading;
  }

  public Date getResulttesttime() {
    return resulttesttime;
  }

  public void setResulttesttime(Date resulttesttime) {
    this.resulttesttime = resulttesttime;
  }

  public int getResulttestnumbercorrect() {
    return resulttestnumbercorrect;
  }

  public void setResulttestnumbercorrect(int resulttestnumbercorrect) {
    this.resulttestnumbercorrect = resulttestnumbercorrect;
  }

  public int getResulttestnumberincorrect() {
    return resulttestnumberincorrect;
  }

  public void setResulttestnumberincorrect(int resulttestnumberincorrect) {
    this.resulttestnumberincorrect = resulttestnumberincorrect;
  }

  public PracticeTest getPracticetest() {
    return practicetest;
  }

  public void setPracticetest(PracticeTest practicetest) {
    this.practicetest = practicetest;
  }

  public UserToeic getUser() {
    return user;
  }

  public void setUser(UserToeic user) {
    this.user = user;
  }

  public ResultTest(int resulttestid, int resulttestcorrectlistening, int resulttestcorrectreading,
      Date resulttesttime, int resulttestnumbercorrect, int resulttestnumberincorrect,
      PracticeTest practicetest, UserToeic user) {
    this.resulttestid = resulttestid;
    this.resulttestcorrectlistening = resulttestcorrectlistening;
    this.resulttestcorrectreading = resulttestcorrectreading;
    this.resulttesttime = resulttesttime;
    this.resulttestnumbercorrect = resulttestnumbercorrect;
    this.resulttestnumberincorrect = resulttestnumberincorrect;
    this.practicetest = practicetest;
    this.user = user;
  }
}
