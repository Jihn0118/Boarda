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

        // -15 ~ 그 값,  90분이 넘어간다 => 90분 부터 다
        if (0 < time && time <= 90) {
            builder.and(boardgame.playTime.loe(time).and(boardgame.playTime.goe(time - 14)));
        } else if(time > 90){
            builder.and(boardgame.playTime.goe(91));
        }

        if (num > 0 && num <=8) {
            builder.and(boardgame.minNum.loe(num).and(boardgame.maxNum.goe(num)));
        } else if(num > 8){
            builder.and(boardgame.minNum.goe(num));
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
