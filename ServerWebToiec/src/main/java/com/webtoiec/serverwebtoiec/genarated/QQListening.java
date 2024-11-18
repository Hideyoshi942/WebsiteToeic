package com.webtoiec.serverwebtoiec.genarated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import com.webtoiec.serverwebtoiec.entities.QuestionListening;
import javax.annotation.processing.Generated;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QQListening extends EntityPathBase<QuestionListening> {
  private static final PathInits INITS = PathInits.DIRECT2;

  public static final QQListening qQListening = new QQListening("qQListening");

  public final QListening listening;

  public final StringPath questionlisteningask = createString("questionlisteningask");

  public final StringPath questionlisteninganswer1 = createString("questionlisteninganswer1");

  public final StringPath questionlisteninganswer2 = createString("questionlisteninganswer2");

  public final StringPath questionlisteninganswer3 = createString("questionlisteninganswer3");

  public final StringPath questionlisteninganswer4 = createString("questionlisteninganswer4");

  public final StringPath questionlisteninganswercorrect = createString("questionlisteninganswercorrect");

  public final StringPath questionlisteningexplain = createString("questionlisteningexplain");

  public final NumberPath<Long> questionlisteningid = createNumber("questionlisteningid", Long.class);

  public final StringPath questionlisteningimage = createString("questionlisteningimage");

  public final StringPath questionlisteningscript = createString("questionlisteningscript");

  public final StringPath questionlisteningserial = createString("questionlisteningserial");

  public final StringPath questionlisteningaudio = createString("questionlisteningaudio");

  public QQListening(String variable) {
    this(QuestionListening.class, forVariable(variable), INITS);
  }

  public QQListening(Path<? extends QuestionListening> path) {
    this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
  }

  public QQListening(PathMetadata metadata) {
    this(metadata, PathInits.getFor(metadata, INITS));
  }

  public QQListening(PathMetadata metadata, PathInits inits) {
    this(QuestionListening.class, metadata, inits);
  }

  public QQListening(Class<? extends QuestionListening> type, PathMetadata metadata, PathInits inits) {
    super(type, metadata, inits);
    this.listening = inits.isInitialized("listening") ? new QListening(forProperty("listening")) : null;
  }
}
