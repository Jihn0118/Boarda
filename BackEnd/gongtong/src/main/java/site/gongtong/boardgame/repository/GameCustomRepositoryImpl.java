package site.gongtong.boardgame.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.gongtong.boardgame.model.BoardGame;
import site.gongtong.boardgame.model.QBoardGame;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GameCustomRepositoryImpl implements GameCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BoardGame> findWithConditions(int time, int num, String keyword) {
        QBoardGame boardgame = QBoardGame.boardGame;

        BooleanBuilder builder = new BooleanBuilder();

        if (time > 0) {
            builder.and(boardgame.playTime.eq(time));
        }

        if (num > 0) {
            builder.and(boardgame.minNum.loe(num).and(boardgame.maxNum.goe(num)));
        }

        if (!keyword.isEmpty()) {
            builder.and(boardgame.title.contains(keyword));
        }

        return jpaQueryFactory
                .selectFrom(boardgame)
                .where(builder)
                .fetch();
    }

    @Override
    public BoardGame findGameInfo(Integer gameId) {
        QBoardGame boardgame = QBoardGame.boardGame;

        BoardGame game = jpaQueryFactory
                .selectFrom(boardgame)
                .where(boardgame.id.eq(gameId))
                .fetchOne();

        return game;
    }
}
