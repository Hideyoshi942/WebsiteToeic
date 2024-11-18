package com.webtoiec.serverwebtoiec.genarated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import com.webtoiec.serverwebtoiec.entities.CommentGrammar;
import java.util.Date;
import javax.annotation.processing.Generated;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommentGrammar extends EntityPathBase<CommentGrammar> {
  private static final PathInits INITS = PathInits.DIRECT2;

  public static final QCommentGrammar qCommentGrammar = new QCommentGrammar("qCommentGrammar");

  public final QGrammar qGrammar;

  public final StringPath commentgrammarcontent = createString("commentgrammarcontent");

  public final NumberPath<Integer> commentgrammarid = createNumber("commentgrammarid", Integer.class);

  public final DateTimePath<Date> commentgrammartime = createDateTime("commentgrammartime", java.util.Date.class);

  public final QUserToeic qUserToeic;

  public QCommentGrammar(String variable) {
    this(CommentGrammar.class, forVariable(variable), INITS);
  }

  public QCommentGrammar(Path<? extends CommentGrammar> path) {
    this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
  }

  public QCommentGrammar(PathMetadata metadata) {
    this(metadata, PathInits.getFor(metadata, INITS));
  }

  public QCommentGrammar(PathMetadata metadata, PathInits inits) {
    this(CommentGrammar.class, metadata, inits);
  }

  public QCommentGrammar(Class<? extends CommentGrammar> type, PathMetadata metadata, PathInits inits) {
    super(type, metadata, inits);
    this.qGrammar = inits.isInitialized("qGrammar") ? new QGrammar(forProperty("qGrammar")) : null;
    this.qUserToeic = inits.isInitialized("qUserToeic") ? new QUserToeic(forProperty("qUserToeic")) : null;
  }
}
