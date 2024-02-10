package site.gongtong.moim.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMoimMember is a Querydsl query type for MoimMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMoimMember extends EntityPathBase<MoimMember> {

    private static final long serialVersionUID = 308174619L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMoimMember moimMember = new QMoimMember("moimMember");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final site.gongtong.member.model.QMember member;

    public final QMoim moim;

    public QMoimMember(String variable) {
        this(MoimMember.class, forVariable(variable), INITS);
    }

    public QMoimMember(Path<? extends MoimMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMoimMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMoimMember(PathMetadata metadata, PathInits inits) {
        this(MoimMember.class, metadata, inits);
    }

    public QMoimMember(Class<? extends MoimMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new site.gongtong.member.model.QMember(forProperty("member")) : null;
        this.moim = inits.isInitialized("moim") ? new QMoim(forProperty("moim")) : null;
    }

}

