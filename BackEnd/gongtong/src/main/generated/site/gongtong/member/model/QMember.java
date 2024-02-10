package site.gongtong.member.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1965809505L;

    public static final QMember member = new QMember("member1");

    public final StringPath birth = createString("birth");

    public final ComparablePath<Character> gender = createComparable("gender", Character.class);

    public final StringPath id = createString("id");

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Integer> num = createNumber("num", Integer.class);

    public final StringPath password = createString("password");

    public final StringPath profileImage = createString("profileImage");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

