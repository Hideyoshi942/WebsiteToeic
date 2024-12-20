package com.webtoiec.serverwebtoiec.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "commentpostnews")
public class CommentPostNews {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int commentid;

  // Comment Parent Id
  int parentid;

  @ManyToOne
  @JoinColumn(name = "id")
  UserToeic user;

  @ManyToOne
  @JoinColumn(name = "postid")
  PostNews postnews;

  String content;

  String commentpostnewsimage;

  LocalDateTime created;

  LocalDateTime updated;
}
