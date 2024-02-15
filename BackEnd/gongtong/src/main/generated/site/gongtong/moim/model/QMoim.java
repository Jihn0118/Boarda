package site.gongtong.moim.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMoim is a Querydsl query type for Moim
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMoim extends EntityPathBase<Moim> {

    private static final long serialVersionUID = 1428227361L;

    public static final QMoim moim = new QMoim("moim");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> currentNumber = createNumber("currentNumber", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> datetime = createDateTime("datetime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath leaderNickname = createString("leaderNickname");

    public final StringPath location = createString("location");

    public final NumberPath<Integer> number = createNumber("number", Integer.class);

    public final ComparablePath<Character> status = createComparable("status", Character.class);

    public final StringPath title = createString("title");

    public QMoim(String variable) {
        super(Moim.class, forVariable(variable));
    }

    public QMoim(Path<? extends Moim> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMoim(PathMetadata metadata) {
        super(Moim.class, metadata);
    }

}

