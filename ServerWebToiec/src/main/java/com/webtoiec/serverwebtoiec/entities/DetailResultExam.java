package com.webtoiec.serverwebtoiec.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "detailresultexam")
public class DetailResultExam {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int detailresultexamid;

  int detailresultexamquestionid;

  @Column(columnDefinition = "nvarchar(max)")
  String detailresultexamquestion;

  @Column(columnDefinition = "nvarchar(max)")
  String detailresultexamanswercorrect;

  @Column(columnDefinition = "nvarchar(max)")
  String detailresultexamansweruser;

  @JoinColumn(name = "resulttestid", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ManyToOne
  ResultTest resultTest;

  @JoinColumn(name = "id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @ManyToOne
  UserToeic user;
}
