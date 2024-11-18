package com.webtoiec.serverwebtoiec.genarated;


import static com.querydsl.core.types.PathMetadataFactory.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.PathMetadata;
import com.webtoiec.serverwebtoiec.entities.QuestionReading;
import com.webtoiec.serverwebtoiec.entities.Reading;
import jakarta.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QReading extends EntityPathBase<Reading> {

  public static final QReading reading = new QReading("reading");

  public final NumberPath<Integer> readinglevel = createNumber("readinglevel", Integer.class);

  public final NumberPath<Long> readingdi = createNumber("readingid", Long.class);

  public final ListPath<QuestionReading, QQReading> listQuestionReading = this.<QuestionReading, QQReading>createList("listQuestionReading", QuestionReading.class, QQReading.class, PathInits.DIRECT2);

  public final NumberPath<Integer> readingpart = createNumber("readingpart", Integer.class);

  public final StringPath readingscript = createString("readingscript");

  public final StringPath readingname = createString("readingname");

  public QReading(String variable) {
    super(Reading.class, forVariable(variable));
  }

  public QReading(Path<? extends Reading> path) {
    super(path.getType(), path.getMetadata());
  }

  public QReading(PathMetadata metadata) {
    super(Reading.class, metadata);
  }
}
