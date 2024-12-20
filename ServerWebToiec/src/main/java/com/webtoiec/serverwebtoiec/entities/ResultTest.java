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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Setter
@Getter
@Entity
@Table(name = "resulttest")
public class ResultTest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int resulttestid;

  int resulttestcorrectlistening;

  int resulttestcorrectreading;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
  Date resulttesttime;

  int resulttestnumbercorrect;

  int resulttestnumberincorrect;

  @Column(columnDefinition = "nvarchar(max)")
  String resulttestadvise;

  @ManyToOne
  @JoinColumn(name = "practicetestid", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  PracticeTest practicetest;

  @ManyToOne
  @JoinColumn(name = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  UserToeic user;

}
