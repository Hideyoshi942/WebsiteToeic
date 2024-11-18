package com.webtoiec.serverwebtoiec.genarated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import com.webtoiec.serverwebtoiec.entities.VocabularyLessonContent;
import javax.annotation.processing.Generated;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QVocabularyLessonContent extends EntityPathBase<VocabularyLessonContent> {
  private static final PathInits INITS = PathInits.DIRECT2;

  public static final QVocabularyLessonContent qVocabularyLessonContent = new QVocabularyLessonContent("qVocabularyLessonContent");

  public final StringPath vocabularylessoncontentaudio = createString("vocabularylessoncontentaudio");

  public final QVocabulary qVocabulary;

  public final StringPath vocabularylessoncontentdescript = createString("vocabularylessoncontentdescript");

  public final StringPath vocabularylessoncontentimage = createString("vocabularylessoncontentimage");

  public final StringPath vocabularylessoncontentmeaning = createString("vocabularylessoncontentmeaning");

  public final NumberPath<Integer> vocabularylessoncontentid = createNumber("vocabularylessoncontentid", Integer.class);

  public final NumberPath<Integer> vocabularylessoncontentnumber = createNumber("vocabularylessoncontentnumber", Integer.class);

  public final StringPath vocabularylessoncontentsentence = createString("vocabularylessoncontentsentence");

  public final StringPath vocabularylessoncontenttranscribe = createString("vocabularylessoncontenttranscribe");

  public QVocabularyLessonContent(String variable) {
    this(VocabularyLessonContent.class, forVariable(variable), INITS);
  }

  public QVocabularyLessonContent(Path<? extends VocabularyLessonContent> path) {
    this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
  }

  public QVocabularyLessonContent(PathMetadata metadata) {
    this(metadata, PathInits.getFor(metadata, INITS));
  }

  public QVocabularyLessonContent(PathMetadata metadata, PathInits inits) {
    this(VocabularyLessonContent.class, metadata, inits);
  }

  public QVocabularyLessonContent(Class<? extends VocabularyLessonContent> type, PathMetadata metadata, PathInits inits) {
    super(type, metadata, inits);
    this.qVocabulary = inits.isInitialized("qVocabulary") ? new QVocabulary(forProperty("qVocabulary")) : null;
  }
}
