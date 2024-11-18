package com.webtoiec.serverwebtoiec.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "practicetest")
public class PracticeTest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int practicetestid;

  private String practicetestname;

  private String practicetestimage;

  public PracticeTest() {
  }

  @Override
  public String toString() {
    return "PracticeTest{" +
        "practicetestid=" + practicetestid +
        ", practicetestname='" + practicetestname + '\'' +
        ", practicetestimage='" + practicetestimage + '\'' +
        '}';
  }

  public int getPracticetestid() {
    return practicetestid;
  }

  public void setPracticetestid(int practicetestid) {
    this.practicetestid = practicetestid;
  }

  public String getPracticetestname() {
    return practicetestname;
  }

  public void setPracticetestname(String practicetestname) {
    this.practicetestname = practicetestname;
  }

  public String getPracticetestimage() {
    return practicetestimage;
  }

  public void setPracticetestimage(String practicetestimage) {
    this.practicetestimage = practicetestimage;
  }

  public PracticeTest(int practicetestid, String practicetestname, String practicetestimage) {
    this.practicetestid = practicetestid;
    this.practicetestname = practicetestname;
    this.practicetestimage = practicetestimage;
  }
}
