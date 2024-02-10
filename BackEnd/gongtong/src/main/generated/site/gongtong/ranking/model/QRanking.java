package site.gongtong.ranking.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRanking is a Querydsl query type for Ranking
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRanking extends EntityPathBase<Ranking> {

    private static final long serialVersionUID = -508082553L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRanking ranking = new QRanking("ranking");

    public final NumberPath<Integer> cafeId = createNumber("cafeId", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath flag = createString("flag");

    public final site.gongtong.boardgame.model.QBoardGame game;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> num = createNumber("num", Integer.class);

    public final site.gongtong.review.model.QReview review;

    public final site.gongtong.review.model.QTag tag;

    public QRanking(String variable) {
        this(Ranking.class, forVariable(variable), INITS);
    }

    public QRanking(Path<? extends Ranking> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRanking(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRanking(PathMetadata metadata, PathInits inits) {
        this(Ranking.class, metadata, inits);
    }

    public QRanking(Class<? extends Ranking> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.game = inits.isInitialized("game") ? new site.gongtong.boardgame.model.QBoardGame(forProperty("game")) : null;
        this.review = inits.isInitialized("review") ? new site.gongtong.review.model.QReview(forProperty("review"), inits.get("review")) : null;
        this.tag = inits.isInitialized("tag") ? new site.gongtong.review.model.QTag(forProperty("tag"), inits.get("tag")) : null;
    }

}

