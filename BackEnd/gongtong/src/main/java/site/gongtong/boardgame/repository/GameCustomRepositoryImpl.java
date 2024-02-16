package site.gongtong.boardgame.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.gongtong.Image.model.QImage;
import site.gongtong.boardgame.model.BoardGame;
import site.gongtong.boardgame.model.QBoardGame;
import site.gongtong.review.model.ImageReviewDto;
import site.gongtong.review.model.QReview;
import site.gongtong.review.model.QTag;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GameCustomRepositoryImpl implements GameCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BoardGame> findWithConditions(int time, int num, String keyword) {
        QBoardGame boardgame = QBoardGame.boardGame;

        BooleanBuilder builder = new BooleanBuilder();

        if (0 < time && time <= 90) {
            builder.and(boardgame.playTime.loe(time).and(boardgame.playTime.goe(time - 14)));
        } else if (time > 90) {
            builder.and(boardgame.playTime.goe(91));
        }

        if (0 < num && num <= 8) {
            builder.and(boardgame.minNum.loe(num).and(boardgame.maxNum.goe(num)));
        } else if (num > 8) {
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
    public List<ImageReviewDto> getImagesAndReviewIdsByGameId(Integer gameId) {
        QImage image = QImage.image;
        QReview qReview = QReview.review;
        QTag qTag = QTag.tag;
        return jpaQueryFactory
                .select(Projections.constructor(ImageReviewDto.class, image, qTag.review))
                .from(qTag)
                .join(qTag.review, qReview)
                .join(qReview.images, image)
                .where(qTag.game.id.eq(gameId))
                .fetch();
    }

    @Override
    public List<BoardGame> findAllByTitle(List<String> nameList) {
        QBoardGame boardgame3 = QBoardGame.boardGame;
        return jpaQueryFactory
                .selectFrom(boardgame3)
                .where(boardgame3.title.in(nameList))
                .fetch();
    }

    @Override
    public List<String> findNameList() {
        QBoardGame boardgame4 = QBoardGame.boardGame;

        return jpaQueryFactory
                .select(boardgame4.title)
                .from(boardgame4)
                .fetch();
    }

    @Override
    public BoardGame findById(Integer gameId) {
        QBoardGame qBoardGame = QBoardGame.boardGame;

        return jpaQueryFactory
                .selectFrom(qBoardGame)
                .where(qBoardGame.id.eq(gameId))
                .fetchOne();
    }

}
