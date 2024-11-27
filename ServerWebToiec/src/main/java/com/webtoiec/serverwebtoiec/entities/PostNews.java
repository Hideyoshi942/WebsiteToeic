package com.webtoiec.serverwebtoiec.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "postnews")
public class PostNews {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int postid;

  String title;

  @Column(columnDefinition = "nvarchar(max)")
  String content;

  String postimage;

  LocalDateTime created;

  @ManyToOne
  @JoinColumn(name = "id")
  UserToeic user;
}
