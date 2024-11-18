package com.webtoiec.serverwebtoiec.genarated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import com.webtoiec.serverwebtoiec.entities.CommentVocabulary;
import java.util.Date;
import javax.annotation.processing.Generated;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommentVocabulay extends EntityPathBase<CommentVocabulary> {
  private static final PathInits INITS = PathInits.DIRECT2;

  public static final QCommentVocabulay qCommentVocabulay = new QCommentVocabulay("qCommentVocabulay");

  public final QVocabulary qVocabulary;

  public final StringPath commentvocabularycontent = createString("commentvocabularycontent");

  public final NumberPath<Integer> commentvocabularyid = createNumber("commentvocabularyid", Integer.class);

  public final DateTimePath<Date> commentvocabularytime = createDateTime("commentvocabularytime", java.util.Date.class);

  public final QUserToeic qUserToeic;

  public QCommentVocabulay(String variable) {
    this(CommentVocabulary.class, forVariable(variable), INITS);
  }

  public QCommentVocabulay(Path<? extends CommentVocabulary> path) {
    this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
  }

  public QCommentVocabulay(PathMetadata metadata) {
    this(metadata, PathInits.getFor(metadata, INITS));
  }

  public QCommentVocabulay(PathMetadata metadata, PathInits inits) {
    this(CommentVocabulary.class, metadata, inits);
  }

  public QCommentVocabulay(Class<? extends CommentVocabulary> type, PathMetadata metadata, PathInits inits) {
    super(type, metadata, inits);
    this.qVocabulary = inits.isInitialized("qVocabulary") ? new QVocabulary(forProperty("qVocabulary")) : null;
    this.qUserToeic = inits.isInitialized("qUserToeic") ? new QUserToeic(forProperty("qUserToeic")) : null;
  }
}
