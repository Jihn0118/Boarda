package site.gongtong.boardgame.repository;

import site.gongtong.boardgame.model.BoardGame;
import site.gongtong.review.model.ImageReviewDto;

import java.util.List;

public interface GameCustomRepository {
    List<BoardGame> findWithConditions(int time, int num, String keyword);

    List<BoardGame> findAllByTitle(List<String> nameList);

    List<String> findNameList();

    List<ImageReviewDto> getImagesAndReviewIdsByGameId(Integer gameId);

    BoardGame findById(Integer gameId);
}
