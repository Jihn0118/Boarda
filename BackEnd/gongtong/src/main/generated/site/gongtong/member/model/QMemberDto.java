package site.gongtong.member.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberDto is a Querydsl query type for MemberDto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberDto extends EntityPathBase<MemberDto> {

    private static final long serialVersionUID = 1551951550L;

    public static final QMemberDto memberDto = new QMemberDto("memberDto");

    public final StringPath birth = createString("birth");

    public final ComparablePath<Character> gender = createComparable("gender", Character.class);

    public final StringPath id = createString("id");

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Integer> num = createNumber("num", Integer.class);

    public final StringPath password = createString("password");

    public final StringPath profileImage = createString("profileImage");

    public final EnumPath<site.gongtong.member.model.constant.RoleType> roleType = createEnum("roleType", site.gongtong.member.model.constant.RoleType.class);

    public QMemberDto(String variable) {
        super(MemberDto.class, forVariable(variable));
    }

    public QMemberDto(Path<? extends MemberDto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberDto(PathMetadata metadata) {
        super(MemberDto.class, metadata);
    }

}

