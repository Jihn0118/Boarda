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

    public final NumberPath<Long> id = createNumber("id", Long.class);

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

