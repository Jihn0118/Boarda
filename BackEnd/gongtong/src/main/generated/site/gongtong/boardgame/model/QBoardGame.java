package site.gongtong.boardgame.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardGame is a Querydsl query type for BoardGame
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardGame extends EntityPathBase<BoardGame> {

    private static final long serialVersionUID = -757884373L;

    public static final QBoardGame boardGame = new QBoardGame("boardGame");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final NumberPath<Float> difficulty = createNumber("difficulty", Float.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath image = createString("image");

    public final NumberPath<Integer> maxNum = createNumber("maxNum", Integer.class);

    public final NumberPath<Integer> minNum = createNumber("minNum", Integer.class);

    public final NumberPath<Integer> playTime = createNumber("playTime", Integer.class);

    public final StringPath title = createString("title");

    public final ComparablePath<java.time.Year> year = createComparable("year", java.time.Year.class);

    public QBoardGame(String variable) {
        super(BoardGame.class, forVariable(variable));
    }

    public QBoardGame(Path<? extends BoardGame> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardGame(PathMetadata metadata) {
        super(BoardGame.class, metadata);
    }

}

