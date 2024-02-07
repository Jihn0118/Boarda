package site.gongtong.review.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -329841631L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final site.gongtong.cafe.model.QCafe cafe;

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<site.gongtong.Image.model.Image, site.gongtong.Image.model.QImage> images = this.<site.gongtong.Image.model.Image, site.gongtong.Image.model.QImage>createList("images", site.gongtong.Image.model.Image.class, site.gongtong.Image.model.QImage.class, PathInits.DIRECT2);

    public final BooleanPath isRemoved = createBoolean("isRemoved");

    public final site.gongtong.member.model.QMember member;

    public final site.gongtong.moim.model.QMoim moim;

    public final NumberPath<Float> rate = createNumber("rate", Float.class);

    public final ComparablePath<Character> status = createComparable("status", Character.class);

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cafe = inits.isInitialized("cafe") ? new site.gongtong.cafe.model.QCafe(forProperty("cafe")) : null;
        this.member = inits.isInitialized("member") ? new site.gongtong.member.model.QMember(forProperty("member")) : null;
        this.moim = inits.isInitialized("moim") ? new site.gongtong.moim.model.QMoim(forProperty("moim")) : null;
    }

}

