package com.webtoiec.serverwebtoiec.genarated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import com.webtoiec.serverwebtoiec.entities.QuestionReading;
import jakarta.annotation.Generated;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QQReading extends EntityPathBase<QuestionReading> {

  private static final PathInits INITS = PathInits.DIRECT2;

  public static final QQReading qQReading = new QQReading("qQReading");

  public final QReading reading;

  public final StringPath questionreadingask = createString("questionreadingask");

  public final StringPath questionreadinganswer1 = createString("questionreadinganswer1");

  public final StringPath questionreadinganswer2 = createString("questionreadinganswer2");

  public final StringPath questionreadinganswer3 = createString("questionreadinganswer3");

  public final StringPath questionreadinganswer4 = createString("questionreadinganswer4");

  public final StringPath questionreadinganswercorrect = createString("questionreadinganswercorrect");

  public final StringPath questionreadingexplain = createString("questionreadingexplain");

  public final NumberPath<Long> questionreadingid = createNumber("questionreadingid", Long.class);

  public final StringPath questionreadingscript = createString("questionreadingscript");

  public final StringPath questionreadingserial = createString("questionreadingserial");

  public QQReading(String variable) {
    this(QuestionReading.class, forVariable(variable), INITS);
  }

  public QQReading(Path<? extends QuestionReading> path) {
    this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
  }

  public QQReading(PathMetadata metadata) {
    this(metadata, PathInits.getFor(metadata, INITS));
  }

  public QQReading(PathMetadata metadata, PathInits inits) {
    this(QuestionReading.class, metadata, inits);
  }

  public QQReading(Class<? extends QuestionReading> type, PathMetadata metadata, PathInits inits) {
    super(type, metadata, inits);
    this.reading = inits.isInitialized("reading") ? new QReading(forProperty("reading")) : null;
  }

}
