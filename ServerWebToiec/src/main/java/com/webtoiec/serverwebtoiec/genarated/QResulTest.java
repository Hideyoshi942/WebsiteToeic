package com.webtoiec.serverwebtoiec.genarated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.webtoiec.serverwebtoiec.entities.ResultTest;
import java.util.Date;
import javax.annotation.processing.Generated;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QResulTest extends EntityPathBase<ResultTest> {
  private static final PathInits INITS = PathInits.DIRECT2;

  public static final QResulTest qResulTest = new QResulTest("qResulTest");

  public final QPracticeTest qPracticeTest;

  public final NumberPath<Integer> resulttestcorrectlistening = createNumber("resulttestcorrectlistening", Integer.class);

  public final NumberPath<Integer> resulttestcorrectreading = createNumber("resulttestcorrectreading", Integer.class);

  public final NumberPath<Integer> resulttestid = createNumber("resulttestid", Integer.class);

  public final DateTimePath<Date> resulttesttime = createDateTime("resulttesttime", java.util.Date.class);

  public final QUserToeic qUserToeic;

  public final NumberPath<Integer> resulttestnumbercorrect = createNumber("resulttestnumbercorrect", Integer.class);

  public final NumberPath<Integer> resulttestnumberincorrect = createNumber("resulttestnumberincorrect", Integer.class);

  public QResulTest(String variable) {
    this(ResultTest.class, forVariable(variable), INITS);
  }

  public QResulTest(Path<? extends ResultTest> path) {
    this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
  }

  public QResulTest(PathMetadata metadata) {
    this(metadata, PathInits.getFor(metadata, INITS));
  }

  public QResulTest(PathMetadata metadata, PathInits inits) {
    this(ResultTest.class, metadata, inits);
  }

  public QResulTest(Class<? extends ResultTest> type, PathMetadata metadata, PathInits inits) {
    super(type, metadata, inits);
    this.qPracticeTest = inits.isInitialized("qPracticeTest") ? new QPracticeTest(forProperty("qPracticeTest")) : null;
    this.qUserToeic = inits.isInitialized("qUserToeic") ? new QUserToeic(forProperty("qUserToeic")) : null;
  }
}
