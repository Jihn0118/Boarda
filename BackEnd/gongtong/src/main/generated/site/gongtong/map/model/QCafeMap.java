package site.gongtong.map.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCafeMap is a Querydsl query type for CafeMap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCafeMap extends EntityPathBase<CafeMap> {

    private static final long serialVersionUID = -933891466L;

    public static final QCafeMap cafeMap = new QCafeMap("cafeMap");

    public final StringPath branch = createString("branch");

    public final StringPath brand = createString("brand");

    public final StringPath contact = createString("contact");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath image = createString("image");

    public final StringPath latitude = createString("latitude");

    public final StringPath location = createString("location");

    public final StringPath longitude = createString("longitude");

    public final NumberPath<Float> rate = createNumber("rate", Float.class);

    public QCafeMap(String variable) {
        super(CafeMap.class, forVariable(variable));
    }

    public QCafeMap(Path<? extends CafeMap> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCafeMap(PathMetadata metadata) {
        super(CafeMap.class, metadata);
    }

}

