package com.webtoiec.serverwebtoiec.genarated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import javax.annotation.processing.Generated;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QVocabulary extends EntityPathBase<Vocabulary> {
  public static final QVocabulary vocabulary = new QVocabulary("vocabulary");

  public final StringPath vocabularyimage = createString("vocabularyimage");

  public final NumberPath<Integer> vocabularyid = createNumber("vocabularyid", Integer.class);

  public final StringPath vocabularyname = createString("vocabularyname");

  public QVocabulary(String variable) {
    super(Vocabulary.class, forVariable(variable));
  }

  public QVocabulary(Path<? extends Vocabulary> path) {
    super(path.getType(), path.getMetadata());
  }

  public QVocabulary(PathMetadata metadata) {
    super(Vocabulary.class, metadata);
  }

}
