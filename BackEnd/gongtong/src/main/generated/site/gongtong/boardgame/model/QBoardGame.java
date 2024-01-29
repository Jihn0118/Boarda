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

    public final NumberPath<Long> id = createNumber("id", Long.class);

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

