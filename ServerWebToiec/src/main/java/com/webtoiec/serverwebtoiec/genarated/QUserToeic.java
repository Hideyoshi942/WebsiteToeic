package com.webtoiec.serverwebtoiec.genarated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.webtoiec.serverwebtoiec.entities.Role;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import javax.annotation.processing.Generated;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserToeic extends EntityPathBase<UserToeic> {
  public static final QUserToeic qUserToeic = new QUserToeic("qUserToeic");

  public final StringPath address = createString("address");

  public final StringPath email = createString("email");

  public final StringPath username = createString("username");

  public final NumberPath<Long> id = createNumber("id", Long.class);

  public final StringPath password = createString("password");

  public final StringPath phonenumber = createString("phonenumber");

  public final EnumPath<Role> role = createEnum("role", Role.class);

  public QUserToeic(String variable) {
    super(UserToeic.class, forVariable(variable));
  }

  public QUserToeic(Path<? extends UserToeic> path) {
    super(path.getType(), path.getMetadata());
  }

  public QUserToeic(PathMetadata metadata) {
    super(UserToeic.class, metadata);
  }
}
