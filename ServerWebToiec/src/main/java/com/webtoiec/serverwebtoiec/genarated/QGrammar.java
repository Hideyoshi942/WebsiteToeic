package com.webtoiec.serverwebtoiec.genarated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.webtoiec.serverwebtoiec.entities.Grammar;
import javax.annotation.processing.Generated;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QGrammar extends EntityPathBase<Grammar> {
  public static final QGrammar grammar = new QGrammar("grammar");

  public final StringPath grammarimage = createString("grammarimage");

  public final NumberPath<Integer> grammarid = createNumber("grammarid", Integer.class);

  public final StringPath grammarcontenthtml = createString("grammarcontenthtml");

  public final StringPath grammarcontentmarkdown = createString("grammarcontentmarkdown");

  public final StringPath grammarname = createString("grammarname");

  public QGrammar(String variable) {
    super(Grammar.class, forVariable(variable));
  }

  public QGrammar(Path<? extends Grammar> path) {
    super(path.getType(), path.getMetadata());
  }

  public QGrammar(PathMetadata metadata) {
    super(Grammar.class, metadata);
  }
}
