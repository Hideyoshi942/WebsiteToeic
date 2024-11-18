package com.webtoiec.serverwebtoiec.genarated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import com.webtoiec.serverwebtoiec.entities.QuestionPracticeTest;
import javax.annotation.processing.Generated;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QQPacticeTest extends EntityPathBase<QuestionPracticeTest> {
  private static final PathInits INITS = PathInits.DIRECT2;

  public static final QQPacticeTest qQPracticeTest = new QQPacticeTest("qQPracticeTest");

  public final StringPath questionpracticetestaudio = createString("questionpracticetestaudio");

  public final QPracticeTest practiceTest;

  public final NumberPath<Integer> questionpracticetestid = createNumber("questionpracticetestid", Integer.class);

  public final StringPath questionpracticetestanswercorect = createString("questionpracticetestanswercorect");

  public final StringPath questionpracticetestansweruser = createString("questionpracticetestansweruser");

  public final StringPath questionpracticetestimage = createString("questionpracticetestimage");

  public final NumberPath<Integer> questionpracticetestnumber = createNumber("questionpracticetestnumber", Integer.class);

  public final StringPath questionpracticetestanswer1 = createString("questionpracticetestanswer1");

  public final StringPath questionpracticetestanswer2 = createString("questionpracticetestanswer2");

  public final StringPath questionpracticetestanswer3 = createString("questionpracticetestanswer3");

  public final StringPath questionpracticetestanswer4 = createString("questionpracticetestanswer4");

  public final StringPath questionpracticetestparagraph = createString("questionpracticetestparagraph");

  public final StringPath questionpracticetestask = createString("questionpracticetestask");

  public QQPacticeTest(String variable) {
    this(QuestionPracticeTest.class, forVariable(variable), INITS);
  }

  public QQPacticeTest(Path<? extends QuestionPracticeTest> path) {
    this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
  }

  public QQPacticeTest(PathMetadata metadata) {
    this(metadata, PathInits.getFor(metadata, INITS));
  }

  public QQPacticeTest(PathMetadata metadata, PathInits inits) {
    this(QuestionPracticeTest.class, metadata, inits);
  }

  public QQPacticeTest(Class<? extends QuestionPracticeTest> type, PathMetadata metadata, PathInits inits) {
    super(type, metadata, inits);
    this.practiceTest = inits.isInitialized("practiceTest") ? new QPracticeTest(forProperty("practiceTest")) : null;
  }
}
