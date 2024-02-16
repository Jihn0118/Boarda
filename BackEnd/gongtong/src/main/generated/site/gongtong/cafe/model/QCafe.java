package site.gongtong.cafe.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCafe is a Querydsl query type for Cafe
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCafe extends EntityPathBase<Cafe> {

    private static final long serialVersionUID = 1867700417L;

    public static final QCafe cafe = new QCafe("cafe");

    public final StringPath branch = createString("branch");

    public final StringPath brand = createString("brand");

    public final StringPath contact = createString("contact");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath image = createString("image");

    public final StringPath latitude = createString("latitude");

    public final StringPath location = createString("location");

    public final StringPath longitude = createString("longitude");

    public final NumberPath<Float> rate = createNumber("rate", Float.class);

    public QCafe(String variable) {
        super(Cafe.class, forVariable(variable));
    }

    public QCafe(Path<? extends Cafe> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCafe(PathMetadata metadata) {
        super(Cafe.class, metadata);
    }

}

