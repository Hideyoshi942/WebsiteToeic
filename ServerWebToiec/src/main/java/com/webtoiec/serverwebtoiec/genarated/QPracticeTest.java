package com.webtoiec.serverwebtoiec.genarated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.webtoiec.serverwebtoiec.entities.PracticeTest;
import javax.annotation.processing.Generated;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QPracticeTest extends EntityPathBase<PracticeTest> {
  public static final QPracticeTest practiceTest = new QPracticeTest("practiceTest");

  public final StringPath practicetestimage = createString("practicetestimage");

  public final NumberPath<Integer> practicetestid = createNumber("practicetestid", Integer.class);

  public final StringPath practicetestname = createString("practicetestname");

  public QPracticeTest(String variable) {
    super(PracticeTest.class, forVariable(variable));
  }

  public QPracticeTest(Path<? extends PracticeTest> path) {
    super(path.getType(), path.getMetadata());
  }

  public QPracticeTest(PathMetadata metadata) {
    super(PracticeTest.class, metadata);
  }
}
