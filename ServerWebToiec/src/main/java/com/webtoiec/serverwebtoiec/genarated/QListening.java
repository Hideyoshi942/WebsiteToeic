package com.webtoiec.serverwebtoiec.genarated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import com.webtoiec.serverwebtoiec.entities.Listening;
import com.webtoiec.serverwebtoiec.entities.QuestionListening;
import javax.annotation.processing.Generated;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QListening extends EntityPathBase<Listening> {
  public static final QListening listening = new QListening("listening");

  public final NumberPath<Integer> listeninglevel = createNumber("listeninglevel", Integer.class);

  public final NumberPath<Long> listeningid = createNumber("listeningid", Long.class);

  public final ListPath<QuestionListening, QQListening> listQuestionListening = this.<QuestionListening, QQListening>createList("listQuestionListening", QuestionListening.class, QQListening.class, PathInits.DIRECT2);

  public final NumberPath<Integer> listeningpart = createNumber("listeningpart", Integer.class);

  public final StringPath listeningscript = createString("listeningscript");

  public final StringPath listeningname = createString("listeningname");

  public QListening(String variable) {
    super(Listening.class, forVariable(variable));
  }

  public QListening(Path<? extends Listening> path) {
    super(path.getType(), path.getMetadata());
  }

  public QListening(PathMetadata metadata) {
    super(Listening.class, metadata);
  }
}
